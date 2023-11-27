plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
}

android {
    namespace = "khs.onmi.root"
}

dependencies {
    implementation(project(":navigation"))
}