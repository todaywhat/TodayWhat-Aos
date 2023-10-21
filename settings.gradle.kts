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
include(":enterinformation")
include(":main")
include(":notice")
include(":setup")
include(":allergies")
include(":timetable")
include(":tutorials")
