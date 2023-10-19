plugins {
    id("onmi.android.application")
}

android {
    namespace = "com.onmi.aos"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}