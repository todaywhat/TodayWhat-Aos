import java.util.Properties

plugins {
    id("khs.onmi.application")
    id("khs.onmi.hilt")
    id("com.google.gms.google-services")
}

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        load(file.inputStream())
    }
}

android {
    namespace = "khs.onmi.aos"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        versionCode = 17
        versionName = "1.6.2"
    }

    buildTypes {
        debug {
            val key = localProperties.getProperty("AMPLITUDE_DEBUG_API_KEY", "")
            buildConfigField("String", "AMPLITUDE_API_KEY", "\"$key\"")
        }
        release {
            val key = localProperties.getProperty("AMPLITUDE_RELEASE_API_KEY", "")
            buildConfigField("String", "AMPLITUDE_API_KEY", "\"$key\"")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:common-android"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature:root"))
    implementation(project(":feature:widget"))

    implementation(libs.bundles.ktor)
    implementation(libs.androidx.hilt.work)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}
