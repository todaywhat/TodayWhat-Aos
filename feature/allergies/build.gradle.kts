plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.allergies"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:common-android"))

    implementation(libs.bundles.orbit)
    implementation(libs.androidx.hilt.navigation.compose)
}
