import java.util.Properties

plugins {
    id("khs.onmi.application")
    id("khs.onmi.hilt")
    id("com.google.gms.google-services")
}

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) file.inputStream().use { load(it) }
}

android {
    namespace = "khs.onmi.aos"

    defaultConfig {
        versionCode = 18
        versionName = "1.7.0"
    }

    signingConfigs {
        create("release") {
            val storeFilePath = localProperties.getProperty("STORE_FILE")
            if (storeFilePath != null) {
                storeFile = rootProject.file(storeFilePath)
                storePassword = localProperties.getProperty("STORE_PASSWORD")
                keyAlias = localProperties.getProperty("KEY_ALIAS")
                keyPassword = localProperties.getProperty("KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".dev"
            buildConfigField("String", "AMPLITUDE_API_KEY", "\"dev_placeholder\"")
            resValue("string", "app_name", "오늘 뭐임 (Dev)")
        }
        release {
            buildConfigField("String", "AMPLITUDE_API_KEY", "\"prod_placeholder\"")
            resValue("string", "app_name", "오늘 뭐임")
            signingConfig = if (localProperties.getProperty("STORE_FILE") != null) {
                signingConfigs.getByName("release")
            } else {
                signingConfigs.getByName("debug")
            }
        }
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