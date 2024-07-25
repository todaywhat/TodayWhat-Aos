plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.core.database"
}

dependencies {
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
}