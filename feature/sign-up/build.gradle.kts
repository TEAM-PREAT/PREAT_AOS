import org.jetbrains.kotlin.konan.properties.Properties

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
}

@Suppress("UnstableApiUsage")
android {
    buildFeatures {
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    namespace = "com.freetreechair.sign_up"
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

    // Jetpack Navigation Component
    implementation(AndroidXDependencies.navigationFragment)
    implementation(AndroidXDependencies.navigationUI)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // Jetpack Fragment
    implementation(AndroidXDependencies.fragment)

    // Jetpack Compose
    implementation(ComposeDependency.composeUi)
    implementation(ComposeDependency.composeMaterial)
    implementation(ComposeDependency.composeUiTool)

    // Logger - Timber
    implementation(ThirdPartyDependencies.timber)

    // ImageLoading Library
    implementation(ThirdPartyDependencies.coil)
    implementation(ThirdPartyDependencies.glide)

    // RxBinding
    implementation(ThirdPartyDependencies.rxBinding)

    // RxKotlin
    implementation(ThirdPartyDependencies.rxKotlin)

    // Third Party Library
    implementation(ThirdPartyDependencies.lottie)
    implementation(ThirdPartyDependencies.lottieCompose)
}
