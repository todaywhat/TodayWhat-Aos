plugins {
    id("khs.onmi.application")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.aos"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":feature:root"))

    implementation(libs.bundles.ktor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}