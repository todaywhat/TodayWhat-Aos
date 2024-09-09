plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
}

android {
    namespace = "khs.onmi.setting"
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:common-android"))
}