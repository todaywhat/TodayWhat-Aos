plugins {
    id("khs.onmi.library")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.core.database"

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.core)
}