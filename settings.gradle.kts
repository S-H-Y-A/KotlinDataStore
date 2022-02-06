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
////                        # available:"3.2"
////                        # available:"3.2.1"
////                        # available:"3.3"
////                        # available:"3.3.1"
////                        # available:"3.3.2"
////                        # available:"3.3.3"
////                        # available:"3.3.4"
////                        # available:"3.4"
////                        # available:"3.4.1"
////                        # available:"3.5"
////                        # available:"3.5.1"
////                        # available:"3.5.2"
////                        # available:"3.6"
////                        # available:"3.6.1"
////                        # available:"3.6.2"
////                        # available:"3.6.3"
////                        # available:"3.6.4"
////                        # available:"3.7"
////                        # available:"3.7.1"
////                        # available:"3.7.2"
////                        # available:"3.8"
////                        # available:"3.8.1"
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.40.0"
////                            # available:"0.40.1"
}
rootProject.name = "KotlinDataStore"
include(":app")
include(":core")
include(":compose")
include(":initializer")
include(":enum")
include(":gson")
include(":moshi")
