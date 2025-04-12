plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
}

android {
    namespace = "khs.onmi.core.designsystem"
}

dependencies {
    implementation(libs.airbnb.android.lottie)
}