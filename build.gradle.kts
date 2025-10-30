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