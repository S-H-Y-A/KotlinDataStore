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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = BuildInfo.javaVersion.toString()
    }
}

dependencies {
    implementation(project(Deps.Project.kotlinDataStore))
}

afterEvaluate {
    publishing {
        publications {

            // Creates a Maven publication called "release".
            register("release", MavenPublication::class) {
                from(components["release"])
                groupId = BuildInfo.JitPack.groupId
                artifactId = "enum"
                version = BuildInfo.JitPack.version

            }

        }
    }
}