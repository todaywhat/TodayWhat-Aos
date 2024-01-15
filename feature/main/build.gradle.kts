plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.main"
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:database"))

    implementation(project(":domain:timetable:domain"))
    implementation(project(":domain:timetable:data"))

    implementation(project(":domain:meal:domain"))
    implementation(project(":domain:meal:data"))

    implementation(libs.bundles.orbit)
    implementation(libs.hilt.navigation)
}