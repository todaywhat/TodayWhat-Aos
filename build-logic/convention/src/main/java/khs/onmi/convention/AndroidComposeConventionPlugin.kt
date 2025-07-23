package khs.onmi.convention

import com.android.build.api.dsl.LibraryExtension
import khs.onmi.convention.project.configureAndroidCompose
import khs.onmi.convention.project.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }

            dependencies {
                val bom = libs.findLibrary("androidx-compose-bom").get()

                add("implementation", platform(bom))
                add("androidTestImplementation", platform(bom))

                // compose bundle 추후 각 feature가 필요한 libs만 사용하도록 개선
                add("implementation", libs.findBundle("compose").get())
            }
        }
    }
}