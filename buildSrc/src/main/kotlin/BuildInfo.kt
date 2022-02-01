import org.gradle.api.JavaVersion

object BuildInfo {
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = compileSdk
    const val applicationId = "com.s_h_y_a.kotlindatastoresample"
    const val versionCode = 1
    const val versionName = JitPack.version
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val javaVersion = JavaVersion.VERSION_1_8

    object JitPack {
        const val groupId = "com.github.s_h_y_a.kotlinDataStore"
        const val version = "1.0"
    }
}