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

val hasReleaseSigning = !localProperties.getProperty("STORE_FILE").isNullOrBlank()
val isReleaseTaskRequested = gradle.startParameter.taskNames
    .any { it.contains("Release", ignoreCase = true) }

android {
    namespace = "khs.onmi.aos"

    defaultConfig {
        versionCode = 18
        versionName = "1.7.0"
    }

    signingConfigs {
        create("release") {
            if (hasReleaseSigning) {
                val storeFilePath = localProperties.getProperty("STORE_FILE")
                val resolvedStoreFile = rootProject.file(storeFilePath)
                require(resolvedStoreFile.exists()) {
                    "Invalid STORE_FILE: $storeFilePath"
                }

                val storePwd = localProperties.getProperty("STORE_PASSWORD")?.takeIf { it.isNotBlank() }
                val alias = localProperties.getProperty("KEY_ALIAS")?.takeIf { it.isNotBlank() }
                val keyPwd = localProperties.getProperty("KEY_PASSWORD")?.takeIf { it.isNotBlank() }
                require(storePwd != null && alias != null && keyPwd != null) {
                    "Missing release signing properties: STORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD"
                }

                storeFile = resolvedStoreFile
                storePassword = storePwd
                keyAlias = alias
                keyPassword = keyPwd
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
            signingConfig = when {
                hasReleaseSigning -> signingConfigs.getByName("release")
                isReleaseTaskRequested -> error(
                    "Release signing is required for release tasks. " +
                        "Set STORE_FILE/STORE_PASSWORD/KEY_ALIAS/KEY_PASSWORD in local.properties."
                )
                else -> signingConfigs.getByName("debug")
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