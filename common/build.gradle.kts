plugins {
    id("com.android.library")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

@Suppress("UnstableApiUsage")
android {
    buildFeatures {
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }
    namespace = "com.freetreechair.common"
}

dependencies {
    // Modules
    implementation(project(":navigator"))

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.legacy)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.fragment)
    implementation(AndroidXDependencies.splashScreen)
    coreLibraryDesugaring(AndroidXDependencies.desugarLibrary)

    // Jetpack Compose
    implementation(ComposeDependency.composeUi)
    implementation(ComposeDependency.composeMaterial)
    implementation(ComposeDependency.composeUiTool)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // ThirdParty Library
    implementation(ThirdPartyDependencies.lottie)
    implementation(ThirdPartyDependencies.lottieCompose)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // ImageLoading Library
    implementation(ThirdPartyDependencies.coil)
    implementation(ThirdPartyDependencies.glide)

    // RxBinding
    implementation(ThirdPartyDependencies.rxBinding)

    // RxKotlin
    implementation(ThirdPartyDependencies.rxKotlin)

    // Timber
    implementation(ThirdPartyDependencies.timber)

    // Testing
    implementation(TestDependencies.jUnit)
    implementation(TestDependencies.androidTest)
}
