plugins {
    kotlin("jvm") version "2.2.0"
}

group = "me.chriss99"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "me.chriss99.MainKt"
            attributes["Implementation-Version"] = version
        }

        // Avoid the duplicate handling strategy error
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        // Add all the dependencies
        from(sourceSets.main.get().output)

        dependsOn(configurations.runtimeClasspath)
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }

    test {
        useJUnitPlatform()
    }
}
kotlin {
    jvmToolchain(21)
}