pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.fabricmc.net")
        maven("https://maven.architectury.dev")
        maven("https://maven.minecraftforge.net")
        maven("https://repo.essential.gg/repository/maven-public")

    }

    plugins {
        val egtVersion = "0.6.8"
        id("gg.essential.loom") version "1.9.+"
        id("gg.essential.multi-version.root") version egtVersion
        id("com.github.johnrengelman.shadow") version "8.1.1"
    }
}

rootProject.name = "ClickGui"
rootProject.buildFileName = "root.gradle.kts"

listOf(
    "1.8.9-forge",
    "1.12.2-forge",
    "1.16.2-forge",
    "1.16.2-fabric",
    "1.21.5-fabric"
).forEach {
    version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "../../build.gradle.kts"
    }
}
