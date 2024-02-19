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
include(":core:ui")
include(":core:designsystem")
include(":feature:root")
include(":core:database")
include(":data")
include(":domain")
