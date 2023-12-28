plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
    id("kotlin-kapt")
}

android {
    namespace = "khs.onmi.core.database"
}

dependencies {
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
}