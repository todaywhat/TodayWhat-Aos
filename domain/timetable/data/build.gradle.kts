plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "khs.onmi.timetable.data"
}

dependencies {
    implementation(project(":domain:timetable:domain"))

    implementation(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization.json)
}