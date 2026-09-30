---
name: code-writing-guide
description: >
  모든 안드로이드 코드 작성 시 가장 먼저 확인하는 필수 가이드.
  ViewModel 설계, Navigation 패턴, Compose UI, Kotlin 스타일, 접근 제어자 등
  전체 코드베이스에 공통 적용되는 프로젝트 고유 규칙을 다룹니다.
  코드 작성, 리팩토링, 새 기능 구현 시 반드시 참조할 것.
---

# Android Code Writing Guide

안드로이드 코드 작성 시 적용하는 프로젝트 고유 컨벤션 가이드입니다.
표준 Kotlin/Compose 규칙은 Claude가 이미 알고 있으므로, **팀 고유 규칙만** 다룹니다.

## 표준 Kotlin/Compose 컨벤션
Kotlin/Compose 표준 컨벤션은 공식 가이드 내용으로 접근 가능하므로, 아래 URL을 참조합니다.
- Kotlin: https://kotlinlang.org/docs/coding-conventions.html
- Compose: https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-api-guidelines.md

## 대상
- 모든 Kotlin/Compose 코드
- ViewModel, Navigation, UI 컴포넌트

## 카테고리
세부 규칙은 아래 파일로 분류됩니다. 작업과 관련된 카테고리를 참조하세요.
- [STYLE.md](STYLE.md): 코드 스타일 — 상수 네이밍, 접근 제어자, Route→Screen 패턴, UI 컴포넌트 네이밍, ViewModel 초기화 패턴
- [CRITICAL.md](CRITICAL.md): 핵심 규칙 — 크래시·데이터 손상·하위 호환성 등 반드시 지켜야 할 항목
- [PERFORMANCE.md](PERFORMANCE.md): 성능 — recomposition 최소화, Compose 안정성, 불필요한 연산 방지
