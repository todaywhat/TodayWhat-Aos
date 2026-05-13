plugins {
    id("khs.onmi.library")
}

android {
    namespace = "khs.onmi.core.common.android"
}

dependencies {

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}