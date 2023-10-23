package khs.onmi.aos

import java.io.File
import java.io.FileWriter

fun main() {
    val projectPath = System.getProperty("user.dir")!!
    println("write module name.")
    val moduleName = readln()
    val modulePath = "$projectPath/$moduleName"
    val moduleDirectory = File(projectPath, moduleName)

    if (moduleDirectory.exists()) {
        println("[ $moduleName ] is already exist.")
    } else {
        moduleDirectory.mkdir()
        updateSettingGradle(projectPath, moduleName)
        createModuleDirectories(modulePath, moduleName)
        createModuleFiles(modulePath, moduleName)
    }
}

fun makeFile(
    path: String,
    fileName: String,
    content: String = "",
) {
    val file = File(path, fileName)

    if (file.exists()) {
        println("file [ $fileName ] is already exist.")
    } else {
        runCatching {
            val fileWriter = FileWriter(file)
            fileWriter.write(content)
            fileWriter.close()
        }.onFailure {
            println("fail to create file [ $fileName ]\n caused: $it")
        }
    }
}

fun makeDirectory(
    path: String,
    directoryName: String,
) {
    val file = File(path, directoryName)

    if (file.exists()) {
        println("이미 존재하는 이름의 디렉토리입니다.")
    } else {
        runCatching {
            file.mkdir()
        }.onFailure {
            println("fail to create directory [ $directoryName ]\n caused: $it")
        }
    }
}

fun updateSettingGradle(
    path: String,
    moduleName: String,
) {
    val settingGradle = File("$path/settings.gradle.kts")
    val settingGradleContent = settingGradle.readText()
    val fileWriter = FileWriter(settingGradle)
    fileWriter.write("$settingGradleContent\ninclude(\":$moduleName\")")
    fileWriter.close()
}

fun createModuleDirectories(
    modulePath: String,
    moduleName: String,
) {
    val dirs = listOf("java", "khs", "onmi", moduleName)
    val dirNames = listOf("main", "test", "androidTest")

    makeDirectory(path = modulePath, directoryName = "libs")
    makeDirectory(path = modulePath, directoryName = "src")
    dirNames.forEach { name ->
        makeDirectory("$modulePath/src", name)
        var dirPath = "$modulePath/src/$name"

        dirs.forEach {
            makeDirectory(dirPath, it)
            dirPath = "$dirPath/$it"
        }
    }
}

fun createModuleFiles(
    modulePath: String,
    moduleName: String,
) {
    makeFile(path = modulePath, fileName = "build.gradle.kts", content = Content.buildGradleKts(moduleName))
    makeFile(path = modulePath, fileName = ".gitignore")
    makeFile(path = modulePath, fileName = "consumer-rules.pro")
    makeFile(path = modulePath, fileName = "proguard-rules.pro", content = Content.proguardRules)
    makeFile(path = "$modulePath/src/main", fileName = "AndroidManifest.xml", content = Content.manifest)
    makeFile(path = "$modulePath/src/main/java/khs/onmi/$moduleName", fileName = ".gitinit")
    makeFile(path = "$modulePath/src/androidTest/java/khs/onmi/$moduleName", fileName = "ExampleInstrumentedTest.kt")
    makeFile(path = "$modulePath/src/test/java/khs/onmi/$moduleName", fileName = "ExampleUnitTest.kt")
}