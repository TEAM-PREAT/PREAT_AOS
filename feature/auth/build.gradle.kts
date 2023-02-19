import org.jetbrains.kotlin.konan.properties.Properties

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    @Suppress("UnstableApiUsage")
    buildFeatures {
        dataBinding = true
    }
    defaultConfig {
        buildConfigField(
            "String",
            "PREAT_SERVER_BASE_URL_DEBUG",
            properties.getProperty("PREAT_SERVER_BASE_URL_DEBUG")
        )
        buildConfigField(
            "String",
            "PREAT_SERVER_BASE_URL_RELEASE",
            properties.getProperty("PREAT_SERVER_BASE_URL_RELEASE")
        )
        buildConfigField(
            "String",
            "KAKAO_NATIVE_APP_KEY",
            properties.getProperty("KAKAO_NATIVE_APP_KEY")
        )
    }
    namespace = "com.freetreechair.preat.auth"
}


dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":navigator"))

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.splashScreen)

    // ThirdParty Library
    implementation(ThirdPartyDependencies.lottie)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // Jetpack Fragment
    implementation(AndroidXDependencies.fragment)

    // Logger - Timber
    implementation(ThirdPartyDependencies.timber)

    // ImageLoading Library
    implementation(ThirdPartyDependencies.coil)
    implementation(ThirdPartyDependencies.glide)

    // Kakao Login
    implementation(ThirdPartyDependencies.kakaoAuth)
}
