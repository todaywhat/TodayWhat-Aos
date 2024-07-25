plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.onmi.widget"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":domain"))

    implementation(libs.kotlinx.serialization.json)

    // Glance & Compose
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.appwidget.material)
    implementation(libs.compose.material3)

    // Work & Hilt
    implementation(libs.androidx.work.runtime)
    implementation(libs.hilt.work)
}