plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.freetreechair.navigator"
}

dependencies {
    // Android Core
    implementation(AndroidXDependencies.coreKtx)

    // Testing
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)
}
