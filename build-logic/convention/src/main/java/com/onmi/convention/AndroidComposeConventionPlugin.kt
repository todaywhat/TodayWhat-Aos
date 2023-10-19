package com.onmi.convention

import com.android.build.api.dsl.LibraryExtension
import com.onmi.convention.project.configureAndroidCompose
import com.onmi.convention.project.libs
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
                add("implementation", libs.findBundle("compose").get())
            }
        }
    }
}