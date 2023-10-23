package khs.onmi.aos

import java.io.File
import java.io.FileWriter

fun main() {
    val path = System.getProperty("user.dir")
    println(path)
    println("모듈명을 입력해 주세요.")
    val moduleName = readln()
    val moduleDir = File(path, moduleName)
    val proguardRulesContent = "# Add project specific ProGuard rules here.\n" +
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
    val manifestContent = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
            "\n" +
            "</manifest>"
    val buildGradleKtsContent = "@Suppress(\"DSL_SCOPE_VIOLATION\") // TODO: Remove once KTIJ-19369 is fixed\n" +
            "plugins {\n" +
            "    alias(libs.plugins.com.android.library)\n" +
            "    alias(libs.plugins.org.jetbrains.kotlin.android)\n" +
            "}\n" +
            "\n" +
            "android {\n" +
            "    namespace = \"com.onmi.$moduleName\"\n" +
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

    val settingGradle = File("$path/settings.gradle.kts")
    val settingGradleContent = settingGradle.readText()
    val fileWriter = FileWriter(settingGradle)
    fileWriter.write("$settingGradleContent\ninclude(\":$moduleName\")")
    fileWriter.close()

    if (moduleDir.exists()) {
        println("이미 존재하는 이름의 모듈입니다.")
    } else {
        moduleDir.mkdir()
        makeFile(path = "$path/$moduleName", fileName = "build.gradle.kts", content = buildGradleKtsContent)
        makeFile(path = "$path/$moduleName", fileName = ".gitignore")
        makeFile(path = "$path/$moduleName", fileName = "consumer-rules.pro")
        makeFile(path = "$path/$moduleName", fileName = "proguard-rules.pro", content = proguardRulesContent)
        makeDir(path = "$path/$moduleName", dirName = "libs")
        makeDir(path = "$path/$moduleName", dirName = "src")
        makeDir(path = "$path/$moduleName/src", dirName = "main")
        makeDir(path = "$path/$moduleName/src", dirName = "androidTest")
        makeDir(path = "$path/$moduleName/src", dirName = "test")
        makeDir(path = "$path/$moduleName/src/main", dirName = "java")
        makeFile(path = "$path/$moduleName/src/main", fileName = "AndroidManifest.xml", content = manifestContent)
        makeDir(path = "$path/$moduleName/src/androidTest", dirName = "java")
        makeDir(path = "$path/$moduleName/src/test", dirName = "java")
        makeDir(path = "$path/$moduleName/src/main/java", dirName = "khs")
        makeDir(path = "$path/$moduleName/src/androidTest/java", dirName = "khs")
        makeDir(path = "$path/$moduleName/src/test/java", dirName = "khs")
        makeDir(path = "$path/$moduleName/src/main/java/khs", dirName = "onmi")
        makeDir(path = "$path/$moduleName/src/androidTest/java/khs", dirName = "onmi")
        makeDir(path = "$path/$moduleName/src/test/java/khs", dirName = "onmi")
        makeDir(path = "$path/$moduleName/src/main/java/khs/onmi", dirName = moduleName)
        makeDir(path = "$path/$moduleName/src/androidTest/java/khs/onmi", dirName = moduleName)
        makeDir(path = "$path/$moduleName/src/test/java/khs/onmi", dirName = moduleName)
        makeFile(path = "$path/$moduleName/src/main/java/khs/onmi/$moduleName", fileName = ".gitinit")
        makeFile(path = "$path/$moduleName/src/androidTest/java/khs/onmi/$moduleName", fileName = "ExampleInstrumentedTest.kt")
        makeFile(path = "$path/$moduleName/src/test/java/khs/onmi/$moduleName", fileName = "ExampleUnitTest.kt")
    }
}

fun makeFile(
    path: String,
    fileName: String,
    content: String = "",
) {
    val file = File(path, fileName)

    if (file.exists()) {
        println("이미 존재하는 이름의 파일입니다.")
    } else {
        val fileWriter = FileWriter(file)
        fileWriter.write(content)
        fileWriter.close()
    }
}

fun makeDir(
    path: String,
    dirName: String
) {
    val file = File(path, dirName)

    if (file.exists()) {
        println("이미 존재하는 이름입니다.")
    } else {
        file.mkdir()
    }
}