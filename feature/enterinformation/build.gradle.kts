plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.enterinformation"
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:database"))
    implementation(project(":core:common-android"))

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.bundles.orbit)
    implementation(libs.hilt.navigation)
}