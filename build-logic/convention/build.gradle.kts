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
            id = "khs.onmi.application"
            implementationClass = "khs.onmi.convention.AndroidApplicationConventionPlugin"
        }

        register("AndroidLibrary") {
            id = "khs.onmi.library"
            implementationClass = "khs.onmi.convention.AndroidLibraryConventionPlugin"
        }

        register("AndoirdCompose") {
            id = "khs.onmi.compose"
            implementationClass = "khs.onmi.convention.AndroidComposeConventionPlugin"
        }

        register("AndoirdHilt") {
            id = "khs.onmi.hilt"
            implementationClass = "khs.onmi.convention.AndroidHiltConventionPlugin"
        }
    }
}