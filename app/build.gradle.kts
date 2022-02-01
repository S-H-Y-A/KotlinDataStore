import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = BuildInfo.compileSdk

    defaultConfig {
        applicationId = BuildInfo.applicationId
        minSdk = BuildInfo.minSdk
        targetSdk = BuildInfo.targetSdk
        versionCode = BuildInfo.versionCode
        versionName = BuildInfo.versionName

        testInstrumentationRunner = BuildInfo.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(project(Deps.Project.kotlinDataStore))
    implementation(project(Deps.Project.dataStoreCompose))
    implementation(project(Deps.Project.dataStoreMoshi))
    implementation(project(Deps.Project.dataStoreInitializer))
    implementation(project(Deps.Project.dataStoreEnum))

    implementation(AndroidX.core.ktx)
    val composeVersion = versionFor(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.withVersion(composeVersion))
    implementation(AndroidX.compose.material.withVersion(composeVersion))
    implementation(AndroidX.compose.ui.toolingPreview.withVersion(composeVersion))
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.activity.compose)
    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.compose.ui.testJunit4.withVersion(composeVersion))
    debugImplementation(AndroidX.compose.ui.tooling.withVersion(composeVersion))

}