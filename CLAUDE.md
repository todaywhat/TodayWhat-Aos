# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

"오늘 뭐임" (TodayWhat) Android app — shows school meals and timetables sourced from the Korean NEIS open API, plus home-screen widgets. Package `com.onmi.aos`, min SDK 24, target/compile SDK 35, JDK 17, Kotlin 2.1.21, Jetpack Compose.

## Domain Glossary

Use these English terms (matching the actual module/package names — all lowercase) for the corresponding domain concepts in code, identifiers, and commits.

| 한국어 | English (코드 표기) |
| --- | --- |
| 급식 | `meal` |
| 시간표 | `timetable` |
| 설정 | `setting` |
| 알레르기 (급식) | `allergies` |
| 위젯 | `widget` |
| 최초 정보 기입 및 정보 수정 | `enterinformation` |

## Build & Run

```bash
./gradlew assembleDebug          # build debug APK (applicationId com.onmi.aos.dev, app name "오늘 뭐임 (Dev)")
./gradlew assembleRelease        # signed release build — requires keystore props in local.properties
./gradlew :feature:main:compileDebugKotlin   # fast type-check of a single module
./gradlew clean
```

There are currently **no unit or instrumentation tests** in the repo (the `domain` module is pre-wired for JUnit5 via `useJUnitPlatform()`, but no tests exist yet). When adding tests, run them with `./gradlew :<module>:test`.

`local.properties` must define `STORE_FILE`, `STORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD` for release signing (read in `app/build.gradle.kts`). `google-services.json` is required under `app/` for Firebase.

## Module Architecture

Multi-module Clean Architecture. `rootProject.name` is `OnmiAndroid`.

- **`domain`** — pure Kotlin business logic: `usecase/`, `repository/` (interfaces), `model/`, `exception/`. No Android UI deps.
- **`data`** — implements domain repositories: `repository/` (impls), `datasource/` + `datasourceimpl/`, `dto/` (+ `toModel()` mappers), `network/` (Ktor), `di/` (Hilt modules). Talks to NEIS API.
- **`feature:*`** — one Compose feature per module (`main`, `setting`, `allergies`, `timetable`, `enterinformation`, `notice`, `tutorials`). Each depends on `domain`/`data`, `core:*`, and `navigation`.
- **`feature:root`** — hosts `MainActivity` (single-activity), wires every feature's nav graph into one `NavHost`.
- **`feature:widget`** — Glance app-widgets + WorkManager (independent of the Compose UI features).
- **`navigation`** — central route constants only (`ONMINavRoutes`); no Compose deps so every feature can reference routes.
- **`core:designsystem`** (theme/components), **`core:ui`** (shared Compose UI), **`core:common-android`** (`EventLogger`, `SafeUri`).
- **`app`** — assembles `data` + `domain` + `feature:root` + `feature:widget`; owns Firebase/Ktor/WorkManager wiring and `ONMIApplication`.

Dependency direction: `feature → domain/data/core/navigation`; `data → domain`; `domain` depends on nothing.

> Note the package-name split: `app`/`feature`/`core`/`navigation` use namespace **`khs.onmi.*`**, while `domain`/`data` use **`com.onmi.*`**. The Android `applicationId` is `com.onmi.aos`.

## Convention Plugins (build-logic)

Module build files apply convention plugins instead of repeating config. Defined in `build-logic/convention/` and registered in its `build.gradle.kts`:

- `khs.onmi.application` — the `app` module (Android application, `buildConfig`, applicationId, SDK levels).
- `khs.onmi.library` — every library/feature module (Android library + SDK/Java 17 config).
- `khs.onmi.compose` — adds Compose compiler + the `compose` bundle. Apply on any UI module.
- `khs.onmi.hilt` — applies KSP + Hilt and adds the Hilt deps.

Shared SDK/Kotlin/Java-17 settings live in `convention/project/KotlinAndroid.kt` and `AndroidCompose.kt`. A typical feature module:

```kotlin
plugins {
    id("khs.onmi.library"); id("khs.onmi.compose"); id("khs.onmi.hilt")
}
android { namespace = "khs.onmi.<feature>" }
```

Dependencies are managed via the version catalog `gradle/libs.versions.toml` (note bundles: `compose`, `orbit`, `ktor`).

## MVI Pattern (Orbit)

UI state is managed with **Orbit MVI**. Per feature module the convention is:

- `viewmodel/<X>ViewModel.kt` — `@HiltViewModel class ... : ContainerHost<State, SideEffect>, ViewModel()`, with `override val container = container<State, SideEffect>(State())`. Mutate state inside `intent { reduce { state.copy(...) } }`; emit one-shot events with `postSideEffect(...)`.
- `viewmodel/container/<X>State.kt` — immutable `data class` state.
- `viewmodel/container/<X>SideEffect.kt` — sealed one-shot effects (e.g. `ShowToast`).
- `screen/<X>Route.kt` — stateful entry: `hiltViewModel()`, collects `container.stateFlow`, handles side effects, fires `EventLogger.pageShowed(...)`.
- `screen/<X>Screen.kt` — stateless Composable taking `uiState` + lambdas.
- `component/` — feature-local Composables; `navigation/<X>Navigation.kt` — see below.

## Navigation

Single `NavHost` in `MainActivity`. Each feature exposes a `NavGraphBuilder.<feature>NavGraph(navController)` extension (in its `navigation/` package) that calls `navigation(startDestination, route) { composable(...) { Route(...) } }`. All route strings are constants in `navigation/ONMINavRoutes.kt` — add new routes there, never hardcode strings in features. Start destination is chosen at runtime (`ENTERINFOMATION` vs `MAIN`) based on whether the user has entered school info.

## Data / Network Conventions

- **NEIS API** via Ktor (`data/network/KtorClient.kt`): base host `open.neis.go.kr`, JSON, lenient + `ignoreUnknownKeys`.
- UseCases wrap repository calls in `runCatching { ... }.fold(...)` and map results into a **sealed `*State`** (`Loading` / `Success(data)` / `Failure(exception)`) where the failure carries a sealed domain-error type. API error codes are modeled in `domain/exception/NeisResult` and surfaced as `NeisException`; UseCases translate those (plus `UnknownHostException`/`UnresolvedAddressException` → `InternetDisconnected`) into user-facing error states. Follow this pattern for new data flows rather than throwing across layers.
- DTOs in `data/dto/` provide `toModel()` extensions converting to `domain/model` types.

## Widgets

`feature:widget` uses **Glance** + **WorkManager**. Each widget type (`meal`, `timetable`, `combined`) has a `…Widget`, a `…Worker` (refresh job), an `…Info` data holder, and a `…StateDefinition` (Glance state persistence). Widget crashes have been a recurring source of Crashlytics issues — be careful with `GlanceAppWidget` action/trampoline code and external URI launches (`core/common-android/SafeUri.kt`, `feature/widget/util/launchApp.kt`).

## Analytics & Crash Reporting

- **Firebase** Crashlytics + Analytics (BoM-managed) and **Amplitude** (`EventLogger` in `core:common-android`, key via `BuildConfig.AMPLITUDE_API_KEY`). Log screen views with `EventLogger.pageShowed(Screen.X)` from each Route.

## Commit Convention

Commit messages follow gitmoji + Korean style: `<emoji> :: <설명>` (e.g. `🐛 :: 위젯 클릭 시 Glance ActionTrampoline 크래시 방지`). Match the existing style. The main branch is `develop`.

## Additional Guidelines

- **좁은 범위 변경** — 대상 모듈 내 최소한의 수정만. 불필요한 리팩토링 금지.
- **하위 호환성 유지** — SDK 레벨, DB 마이그레이션, 저장 데이터 포맷을 고려해 기존 사용자 데이터를 깨뜨리지 않는다.
- **Compose UI 안정성** — recomposition 이슈와 성능에 주의. 안정적인(stable) 파라미터·상태를 유지해 불필요한 재구성을 방지한다.
