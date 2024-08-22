plugins {
    id("khs.onmi.library")
    id("com.google.gms.google-services")
}

android {
    namespace = "khs.onmi.core.common.android"
}

dependencies {

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}