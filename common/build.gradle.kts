plugins {
    id("com.android.library")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    @Suppress("UnstableApiUsage")
    buildFeatures {
        dataBinding = true
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

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // ThirdParty Library
    implementation(ThirdPartyDependencies.lottie)

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
}
