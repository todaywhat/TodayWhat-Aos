plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
}

android {
    namespace = "khs.onmi.main"
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:database"))

    implementation(libs.bundles.orbit)
    implementation(libs.hilt.navigation)
}