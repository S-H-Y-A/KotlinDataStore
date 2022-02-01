import de.fayard.refreshVersions.core.versionFor

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.ui)
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(AndroidX.compose.ui)
    implementation(project(Deps.Project.kotlinDataStore))
    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
}

afterEvaluate {
    publishing {
        publications {

            // Creates a Maven publication called "release".
            register("release", MavenPublication::class) {
                from(components["release"])
                groupId = BuildInfo.JitPack.groupId
                artifactId = "compose"
                version = BuildInfo.JitPack.version

            }

        }
    }
}