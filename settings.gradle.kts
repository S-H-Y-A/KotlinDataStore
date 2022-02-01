pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
plugins {
    id("com.gradle.enterprise") version "3.1.1"
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.40.0"
}
rootProject.name = "KotlinDataStore"
include(":app")
include(":core")
include(":compose")
include(":initializer")
include(":enum")
include(":gson")
include(":moshi")
