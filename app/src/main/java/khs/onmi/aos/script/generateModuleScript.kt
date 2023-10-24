package khs.onmi.aos.script

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
            "domain" -> { createDomainModules() }
            "core" -> { createCoreModule() }
        }
    } else {
        println("[ $location ] is not exist.")
        return
    }
}

// create file function
fun createFile(
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

//create directory function
fun createDirectory(
    path: String,
    directoryName: String,
) {
    val file = File(path, directoryName)

    if (file.exists()) {
        println("directory [ $directoryName ] is already exist.")
    } else {
        runCatching {
            file.mkdir()
        }.onFailure {
            println("fail to create directory [ $directoryName ]\n caused: $it")
        }
    }
}

//update settings.gradle function
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

//create all internal module directories function
fun createModuleDirectories(
    modulePath: String,
    moduleInternalDirectories: String,
) {
    val dirNames = listOf("main", "test", "androidTest")

    createDirectory(path = modulePath, directoryName = "libs")
    createDirectory(path = modulePath, directoryName = "src")
    dirNames.forEach { name ->
        createDirectory("$modulePath/src", name)
        var dirPath = "$modulePath/src/$name"

        moduleInternalDirectories.split("/").forEach {
            createDirectory(dirPath, it)
            dirPath = "$dirPath/$it"
        }
    }
}

//create all internal module files function
fun createModuleFiles(
    modulePath: String,
    moduleName: String,
    moduleInternalDirectoriesPath: String,
) {
    createFile(path = modulePath, fileName = "build.gradle.kts", content = Content.buildGradleKts(moduleName))
    createFile(path = modulePath, fileName = ".gitignore")
    createFile(path = modulePath, fileName = "consumer-rules.pro")
    createFile(path = modulePath, fileName = "proguard-rules.pro", content = Content.proguardRules)
    createFile(path = "$modulePath/src/main", fileName = "AndroidManifest.xml", content = Content.manifest)
    createFile(path = "$modulePath/src/main/$moduleInternalDirectoriesPath", fileName = ".gitinit")
    createFile(path = "$modulePath/src/androidTest/$moduleInternalDirectoriesPath", fileName = "ExampleInstrumentedTest.kt")
    createFile(path = "$modulePath/src/test/$moduleInternalDirectoriesPath", fileName = "ExampleUnitTest.kt")
}

//rebuild gradle function
fun rebuildGradle() {
    runCatching {
        val parse = "./gradlew build".split("\\s".toRegex())
        val process = ProcessBuilder(*parse.toTypedArray())
            .redirectErrorStream(true)
            .start()
        println(process.inputStream.bufferedReader().readText())
    }.onFailure {
        println("rebuild error")
    }
}

fun createFeatureModule() {
    println("write feature module name.")
    val projectPath = System.getProperty("user.dir")!!
    val moduleName = readln()
    val modulePath = "$projectPath/feature/$moduleName"
    val moduleDirectory = File("$projectPath/feature/", moduleName)
    val moduleInternalDirectories = "java/khs/onmi/$moduleName"

    if (moduleDirectory.exists()) {
        println("[ $moduleName ] module is already exist.")
        return
    } else {
        moduleDirectory.mkdir()
        updateSettingGradle(projectPath, ":feature:$moduleName")
        createModuleDirectories(modulePath, moduleInternalDirectories)
        createModuleFiles(modulePath, moduleName, moduleInternalDirectories)
    }
    println("finish create feature module [ $moduleName ]")
    rebuildGradle()
}

fun createDomainModules() {
    println("write domain name.")
    val projectPath = System.getProperty("user.dir")!!
    val domainName = readln()
    val domainDirectory = File("$projectPath/domain/", domainName)

    if (domainDirectory.exists()) {
        println("[ $domainName ] domain is already exist.")
        return
    } else {
        domainDirectory.mkdir()
    }

    val domainModulePath = "$projectPath/domain/$domainName/domain"
    val dataModulePath = "$projectPath/domain/$domainName/data"
    val domainModuleDirectory = File("$projectPath/domain/$domainName", "domain")
    val dataModuleDirectory = File("$projectPath/domain/$domainName", "data")
    val domainModuleInternalDirectories = "java/khs/onmi/$domainName/domain"
    val dataModuleInternalDirectories = "java/khs/onmi/$domainName/data"

    domainModuleDirectory.mkdir()
    updateSettingGradle(projectPath, ":domain:$domainName:domain")
    createModuleDirectories(domainModulePath, domainModuleInternalDirectories)
    createModuleFiles(domainModulePath, "$domainName.domain", domainModuleInternalDirectories)

    dataModuleDirectory.mkdir()
    updateSettingGradle(projectPath, ":domain:$domainName:data")
    createModuleDirectories(dataModulePath, dataModuleInternalDirectories)
    createModuleFiles(dataModulePath, "$domainName.data", dataModuleInternalDirectories)

    println("finish create domain modules [ $domainName ]")
    rebuildGradle()
}

fun createCoreModule() {
    println("write core module name.")
    val projectPath = System.getProperty("user.dir")!!
    val moduleName = readln()
    val modulePath = "$projectPath/core/$moduleName"
    val moduleDirectory = File("$projectPath/core/", moduleName)
    val moduleInternalDirectories = "java/khs/onmi/core/$moduleName"

    if (moduleDirectory.exists()) {
        println("[ $moduleName ] module is already exist.")
        return
    } else {
        moduleDirectory.mkdir()
        updateSettingGradle(projectPath, ":core:$moduleName")
        createModuleDirectories(modulePath, moduleInternalDirectories)
        createModuleFiles(modulePath, moduleName, moduleInternalDirectories)
    }
    println("finish create core module [ $moduleName ]")
    rebuildGradle()
}