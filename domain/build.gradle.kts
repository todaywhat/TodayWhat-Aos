plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.domain"
}

dependencies {
    implementation(project(":core:database"))
}