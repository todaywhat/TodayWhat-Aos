plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.school.data"
}

dependencies {
    implementation(project(":domain:school:domain"))

    implementation(libs.bundles.ktor)
}