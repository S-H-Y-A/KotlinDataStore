plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
}

android {
    compileSdk = BuildInfo.compileSdk

    defaultConfig {
        minSdk = BuildInfo.minSdk
        targetSdk = BuildInfo.targetSdk

        testInstrumentationRunner = BuildInfo.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = BuildInfo.javaVersion
        targetCompatibility = BuildInfo.javaVersion
    }
    kotlinOptions {
        jvmTarget = BuildInfo.javaVersion.toString()
    }
}

dependencies {
    api(AndroidX.DataStore.preferences)
}

afterEvaluate {
    publishing {
        publications {

            // Creates a Maven publication called "release".
            register("release", MavenPublication::class) {
                from(components["release"])
                groupId = BuildInfo.JitPack.groupId
                artifactId = "core"
                version = BuildInfo.JitPack.version

            }

        }
    }
}
