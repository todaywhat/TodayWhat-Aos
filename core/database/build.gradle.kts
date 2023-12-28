plugins {
    id("khs.onmi.library")
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