plugins {
    kotlin("jvm") version "2.0.21" apply false
    id("gg.essential.loom") version "1.9.+" apply false
    id("gg.essential.multi-version.root")
}

preprocess {
    val fabric12105 = createNode("1.21.5-fabric", 12105, "yarn")
    val fabric11602 = createNode("1.16.2-fabric", 11602, "intermediary")
    val forge11602 = createNode("1.16.2-forge", 11602, "intermediary")
    val forge11202 = createNode("1.12.2-forge", 11202, "intermediary")
    val forge10809 = createNode("1.8.9-forge", 10809, "mcp")

    fabric12105.link(fabric11602)
    fabric11602.link(forge11602)
    forge11602.link(forge11202)
    forge11202.link(forge10809)
}
