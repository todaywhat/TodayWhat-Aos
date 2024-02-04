plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "khs.onmi.data"
}

dependencies {

    implementation(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization.json)
}