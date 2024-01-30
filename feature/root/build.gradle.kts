plugins {
    id("khs.onmi.library")
    id("khs.onmi.compose")
    id("khs.onmi.hilt")
}

android {
    namespace = "khs.onmi.root"
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":feature:enterinformation"))
    implementation(project(":feature:main"))
    implementation(project(":feature:setting"))
    implementation(project(":core:database"))
    implementation(project(":core:designsystem"))

    implementation(libs.accompanist.systemuicontroller)
}