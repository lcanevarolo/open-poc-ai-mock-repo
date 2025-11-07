allprojects {
    configurations.all {
        exclude(group = "com.google.code.gson", module = "gson")
    }
}

subprojects {
    plugins.withId("java") {
        dependencies {
            add("testImplementation", libs.com.google.guava.guava)
        }
    }
    plugins.withId("org.jetbrains.kotlin.jvm") {
        dependencies {
            add("testImplementation", libs.com.google.guava.guava)
        }
    }
}
plugins {
    id("java")
    id("jacoco")
    id("buildlogic.java-conventions")
}
jacoco {
    toolVersion = "0.8.10"
}
tasks.register<JacocoReport>("jacocoRootReport") {
    dependsOn(subprojects.map { it.tasks.named("test") })

    val coverageSourceDirs = files(subprojects.map { it.the<SourceSetContainer>()["main"].allSource.srcDirs })
    val executionDataFiles = files(subprojects.map { it.buildDir.resolve("jacoco/test.exec") })

    sourceDirectories.setFrom(coverageSourceDirs)
    classDirectories.setFrom(files(subprojects.map { it.the<SourceSetContainer>()["main"].output }))
    executionData.setFrom(executionDataFiles)

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}