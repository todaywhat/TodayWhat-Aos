plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
    id("khs.onmi.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.onmi.widget"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:common-android"))
    implementation(project(":domain"))

    implementation(libs.kotlinx.serialization.json)

    // Glance & Compose
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)
    implementation(libs.androidx.compose.material3)

    // Work & Hilt
    implementation(libs.androidx.work)
    implementation(libs.androidx.hilt.work)
    ksp(libs.androidx.hilt.compiler)
}