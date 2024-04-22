plugins {
    id("khs.onmi.application")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.aos"

    defaultConfig {
        versionCode = 3
        versionName = "1.1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature:root"))
    implementation(project(":feature:widget"))

    implementation(libs.bundles.ktor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}