@file:Suppress("UnstableApiUsage")
plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.domain"

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    testImplementation(libs.io.kotest.runner.junit5)
    testImplementation(libs.io.mockk.android)
}