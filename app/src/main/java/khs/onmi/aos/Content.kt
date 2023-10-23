package khs.onmi.aos

object Content {
    //proguard-rule.pro
    const val proguardRules = "# Add project specific ProGuard rules here.\n" +
            "# You can control the set of applied configuration files using the\n" +
            "# proguardFiles setting in build.gradle.\n" +
            "#\n" +
            "# For more details, see\n" +
            "#   http://developer.android.com/guide/developing/tools/proguard.html\n" +
            "\n" +
            "# If your project uses WebView with JS, uncomment the following\n" +
            "# and specify the fully qualified class name to the JavaScript interface\n" +
            "# class:\n" +
            "#-keepclassmembers class fqcn.of.javascript.interface.for.webview {\n" +
            "#   public *;\n" +
            "#}\n" +
            "\n" +
            "# Uncomment this to preserve the line number information for\n" +
            "# debugging stack traces.\n" +
            "#-keepattributes SourceFile,LineNumberTable\n" +
            "\n" +
            "# If you keep the line number information, uncomment this to\n" +
            "# hide the original source file name.\n" +
            "#-renamesourcefileattribute SourceFile"

    //AndroidManifest.xml
    const val manifest = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
            "\n" +
            "</manifest>"

    //build.gradle.kts
    val buildGradleKts: (String) -> String = { moduleName ->
        "@Suppress(\"DSL_SCOPE_VIOLATION\") // TODO: Remove once KTIJ-19369 is fixed\n" +
                "plugins {\n" +
                "    alias(libs.plugins.com.android.library)\n" +
                "    alias(libs.plugins.org.jetbrains.kotlin.android)\n" +
                "}\n" +
                "\n" +
                "android {\n" +
                "    namespace = \"khs.onmi.$moduleName\"\n" +
                "    compileSdk = 33\n" +
                "\n" +
                "    defaultConfig {\n" +
                "        minSdk = 24\n" +
                "\n" +
                "        testInstrumentationRunner = \"androidx.test.runner.AndroidJUnitRunner\"\n" +
                "        consumerProguardFiles(\"consumer-rules.pro\")\n" +
                "    }\n" +
                "\n" +
                "    buildTypes {\n" +
                "        release {\n" +
                "            isMinifyEnabled = false\n" +
                "            proguardFiles(\n" +
                "                getDefaultProguardFile(\"proguard-android-optimize.txt\"),\n" +
                "                \"proguard-rules.pro\"\n" +
                "            )\n" +
                "        }\n" +
                "    }\n" +
                "    compileOptions {\n" +
                "        sourceCompatibility = JavaVersion.VERSION_1_8\n" +
                "        targetCompatibility = JavaVersion.VERSION_1_8\n" +
                "    }\n" +
                "    kotlinOptions {\n" +
                "        jvmTarget = \"1.8\"\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "dependencies {\n" +
                "\n" +
                "    implementation(libs.core.ktx)\n" +
                "    implementation(libs.appcompat)\n" +
                "    implementation(libs.material)\n" +
                "    testImplementation(libs.junit)\n" +
                "    androidTestImplementation(libs.androidx.test.ext.junit)\n" +
                "    androidTestImplementation(libs.espresso.core)\n" +
                "}"
    }
}