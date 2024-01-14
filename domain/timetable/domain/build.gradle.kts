plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.timetable.domain"
}

dependencies {
    implementation(project(":core:database"))
}