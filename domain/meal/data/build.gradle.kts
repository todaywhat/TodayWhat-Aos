plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "khs.onmi.meal.data"
}

dependencies {
    implementation(project(":domain:meal:domain"))

    implementation(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization.json)
}