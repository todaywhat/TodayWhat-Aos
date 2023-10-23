package khs.onmi.aos

import java.io.File
import java.io.FileWriter

fun main() {
    val path = System.getProperty("user.dir")!!
    println(path)
    println("모듈명을 입력해 주세요.")
    val moduleName = readln()
    val modulePath = "$path/$moduleName"
    val moduleDir = File(path, moduleName)
    val dirs = listOf("java","khs","onmi", moduleName)
    val dirNames = listOf("main","test","androidTest")

    writeSettingGradle(path, moduleName)

    if (moduleDir.exists()) {
        println("이미 존재하는 이름의 모듈입니다.")
    } else {
        moduleDir.mkdir()
        makeDir(path = modulePath, dirName = "libs")
        makeDir(path = modulePath, dirName = "src")
        dirNames.forEach { name ->
            makeDir("$modulePath/src", name)
            var dirPath = "$modulePath/src/$name"

            dirs.forEach {
                makeDir(dirPath, it)
                dirPath = "$dirPath/$it"
            }
        }
        makeFile(path = modulePath, fileName = "build.gradle.kts", content = Content.buildGradleKts(moduleName))
        makeFile(path = modulePath, fileName = ".gitignore")
        makeFile(path = modulePath, fileName = "consumer-rules.pro")
        makeFile(path = modulePath, fileName = "proguard-rules.pro", content = Content.proguardRules)
        makeFile(path = "$path/$moduleName/src/main", fileName = "AndroidManifest.xml", content = Content.manifest)
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
        runCatching {
            val fileWriter = FileWriter(file)
            fileWriter.write(content)
            fileWriter.close()
        }.onFailure {
            println("파일 생성에 실패했습니다.\n error: $it")
        }
    }
}

fun makeDir(
    path: String,
    dirName: String
) {
    val file = File(path, dirName)

    if (file.exists()) {
        println("이미 존재하는 이름의 디렉토리입니다.")
    } else {
        runCatching {
            file.mkdir()
        }.onFailure {
            println("디렉토리 생성에 실패했습니다.\n error: $it")
        }
    }
}

fun writeSettingGradle(
    path: String,
    moduleName: String
) {
    val settingGradle = File("$path/settings.gradle.kts")
    val settingGradleContent = settingGradle.readText()
    val fileWriter = FileWriter(settingGradle)
    fileWriter.write("$settingGradleContent\ninclude(\":$moduleName\")")
    fileWriter.close()
}