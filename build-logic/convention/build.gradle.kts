import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplication") {
            id = "onmi.android.application"
            implementationClass = "com.onmi.convention.AndroidApplicationConventionPlugin"
        }

        register("AndroidLibrary") {
            id = "onmi.android.library"
            implementationClass = "com.onmi.convention.AndroidLibraryConventionPlugin"
        }

        register("AndoirdCompose") {
            id = "onmi.android.compose"
            implementationClass = "com.onmi.convention.AndroidComposeConventionPlugin"
        }
    }
}