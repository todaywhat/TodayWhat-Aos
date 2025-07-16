package khs.onmi.convention

import khs.onmi.convention.project.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                add("implementation", libs.findLibrary("dagger.hilt.android").get())
                add("ksp", libs.findLibrary("dagger.hilt.compiler").get())
            }
        }
    }

}