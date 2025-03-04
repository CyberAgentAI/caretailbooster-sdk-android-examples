plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.org.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.github.cyberagentai.caretailbooster_sdk_android_examples"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.cyberagentai.caretailbooster_sdk_android_examples"
        minSdk = 24

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "34.0.0"

    lint {
        targetSdk = 34
    }

    flavorDimensions.add("environment")
    productFlavors {
        create("local") {
            dimension = "environment"
        }
        create("development") {
            dimension = "environment"
        }
        create("staging") {
            dimension = "environment"
        }
        create("production") {
            dimension = "environment"
        }
        create("mock") {
            dimension = "environment"
        }
    }

}

dependencies {
    // Jetpack Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.exoplayer)

    // Kotlin Serialization
    implementation(libs.serialization)

    // Retail Booster SDK
    implementation(libs.caretailbooster)

    compileOnly(libs.androidx.ui.tooling)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
