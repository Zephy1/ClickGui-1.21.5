plugins {
    kotlin("jvm")
    id("maven-publish")
    id("com.github.johnrengelman.shadow")
    id("gg.essential.multi-version")
    id("gg.essential.defaults")
}

group = "dev.debuggings"
version = "2.0.0"

loom {
    runConfigs {
        named("client") {
            ideConfigGenerated(true)
            programArgs("--tweakClass", "gg.essential.loader.stage0.EssentialSetupTweaker")
        }
    }
}

val embed by configurations.creating
configurations.implementation.get().extendsFrom(embed)

repositories {
    maven("https://jitpack.io")
}

dependencies {
    embed("com.electronwill.night-config:toml:3.8.2")

    if (project.platform.mcVersion < 12105) {
        compileOnly("gg.essential:essential-$platform:4167+g4594ad6e6")
        embed("gg.essential:loader-launchwrapper:1.2.3")

        if (project.platform.isFabric) {
            modImplementation("net.fabricmc:fabric-loader:0.11.3")
            modImplementation("net.fabricmc.fabric-api:fabric-api:0.42.0+1.16")
            modImplementation("net.fabricmc:fabric-language-kotlin:1.12.3+kotlin.2.0.21")
        }
    } else {
        modCompileOnly("gg.essential:universalcraft-1.21.5-fabric:421")
        modCompileOnly("gg.essential:elementa:710")
        modImplementation("net.fabricmc:fabric-loader:0.16.4")
        modImplementation("net.fabricmc.fabric-api:fabric-api:0.128.0+1.21.5")
        modImplementation("net.fabricmc:fabric-language-kotlin:1.12.3+kotlin.2.0.21")
    }
}

tasks {
    shadowJar {
        configurations = listOf(embed)
        exclude("com/example/examplemod/**")
        exclude("gg/essential/**")
        relocate("com.electronwill.nightconfig", "dev.debuggings.clickgui.impl.nightconfig")
    }

    remapJar {
        input.set(shadowJar.get().archiveFile)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "dev.debuggings"
            artifactId = "clickgui"

            from(components["java"])
        }
    }
}

preprocess {
    vars.put("FABRIC", if (project.platform.isFabric) 1 else 0)
    vars.put("!FABRIC", if (project.platform.isFabric) 0 else 1)
}
