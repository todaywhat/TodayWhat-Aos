package khs.onmi.aos

import java.io.File
import java.io.FileWriter

fun main() {
    val locations = listOf("feature", "domain", "core")
    println("choose module generate location.")
    println("1.feature\n2.domain\n3.core")
    val location = readln()
    if (locations.contains(location)) {
        when(location) {
            "feature" -> { createFeatureModule() }
            "domain" -> { createDomainModule() }
            "core" -> { createCoreModule() }
        }
    } else {
        println("[ $location ] is not exist.")
        return
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
    modulePath: String,
) {
    val settingGradle = File("$path/settings.gradle.kts")
    val settingGradleContent = settingGradle.readText()
    val fileWriter = FileWriter(settingGradle)
    fileWriter.write("$settingGradleContent\ninclude(\"$modulePath\")")
    fileWriter.close()
}

fun createModuleDirectories(
    modulePath: String,
    moduleInternalDirectories: String,
) {
    val dirNames = listOf("main", "test", "androidTest")

    makeDirectory(path = modulePath, directoryName = "libs")
    makeDirectory(path = modulePath, directoryName = "src")
    dirNames.forEach { name ->
        makeDirectory("$modulePath/src", name)
        var dirPath = "$modulePath/src/$name"

        moduleInternalDirectories.split("/").forEach {
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

fun createFeatureModule() {
    println("write feature module name.")
    val projectPath = System.getProperty("user.dir")!!
    val moduleName = readln()
    val modulePath = "$projectPath/Feature/$moduleName"
    val moduleDirectory = File("$projectPath/Feature/", moduleName)
    val moduleInternalDirectories = "java/khs/onmi/$moduleName"

    if (moduleDirectory.exists()) {
        println("[ $moduleName ] is already exist.")
    } else {
        moduleDirectory.mkdir()
        updateSettingGradle(projectPath, ":feature:$moduleName")
        createModuleDirectories(modulePath, moduleInternalDirectories)
        createModuleFiles(modulePath, moduleName)
    }
    println("finish create feature module [ $moduleName ]")
}

fun createDomainModule() {

}

fun createCoreModule() {

}