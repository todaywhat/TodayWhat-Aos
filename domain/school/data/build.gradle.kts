plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "khs.onmi.school.data"
}

dependencies {
    implementation(project(":domain:school:domain"))

    implementation(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization.json)
}