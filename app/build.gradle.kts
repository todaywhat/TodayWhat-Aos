plugins {
    id("khs.onmi.application")
    id("khs.onmi.hilt")
    id("com.google.gms.google-services")
}

android {
    namespace = "khs.onmi.aos"

    defaultConfig {
        versionCode = 14
        versionName = "1.5.2"
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
    implementation(libs.androidx.hilt.work)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}