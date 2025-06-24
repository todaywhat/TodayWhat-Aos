plugins {
    id("khs.onmi.application")
    id("khs.onmi.hilt")
    id("com.google.gms.google-services")
}

android {
    namespace = "khs.onmi.aos"

    defaultConfig {
        versionCode = 15
        versionName = "1.6.0"
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
    implementation(libs.hilt.work)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}