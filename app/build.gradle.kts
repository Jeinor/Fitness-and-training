plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
}

hilt {
    enableAggregatingTask = false
}

android {
    namespace = "com.example.fitnessandtraining"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fitnessandtraining"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.javapoet)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Hilt
    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.compiler)
    // Room
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    // ViewModel + LiveData
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
}