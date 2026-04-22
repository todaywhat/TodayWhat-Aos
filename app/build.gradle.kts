import java.util.Properties

plugins {
    id("khs.onmi.application")
    id("khs.onmi.hilt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val keyProperties = Properties().apply {
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
            storeFile = rootProject.file(keyProperties.getProperty("STORE_FILE"))
            storePassword = keyProperties.getProperty("STORE_PASSWORD")
            keyAlias = keyProperties.getProperty("KEY_ALIAS")
            keyPassword = keyProperties.getProperty("KEY_PASSWORD")
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
            signingConfig = signingConfigs.getByName("release")
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
    implementation(libs.firebase.crashlytics)
}