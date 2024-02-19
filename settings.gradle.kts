pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OnmiAndroid"
include(":app")
include(":navigation")
include(":feature:enterinformation")
include(":feature:main")
include(":feature:notice")
include(":feature:setting")
include(":feature:allergies")
include(":feature:timetable")
include(":feature:tutorials")
include(":domaind:school:domain")
include(":domaind:school:data")
include(":domaind:department:domain")
include(":domaind:department:data")
include(":domaind:meal:domain")
include(":domaind:meal:data")
include(":domaind:allergies:domain")
include(":domaind:allergies:data")
include(":domaind:notice:domain")
include(":domaind:notice:data")
include(":domaind:student:domain")
include(":domaind:student:data")
include(":domaind:inquire:domain")
include(":domaind:inquire:data")
include(":domaind:system:domain")
include(":domaind:system:data")
include(":domaind:timetable:domain")
include(":domaind:timetable:data")
include(":core:ui")
include(":core:designsystem")
include(":feature:root")
include(":core:database")
include(":data")
include(":domain")
