// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.com.google.devtools.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.com.google.gms.google.services) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
true // Needed to make the Suppress annotation work for the plugins block