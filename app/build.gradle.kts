plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.hospitalsystem"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hospitalsystem"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val okhttp3Version = "4.5.0"
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.compose.material3:material3-android:1.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7")
    implementation("androidx.databinding:adapters:3.2.0-alpha11")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.google.android.material:material:1.11.0")


    implementation("com.google.android.material:material:1.11.0")
    val nav_version = "2.7.7"
    implementation("androidx.compose.material:material:1.6.2")
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    //Constraint layout
    implementation("androidx.constraintlayout:constraintlayout-compose-android:1.1.0-alpha13")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    //Hilt View Model
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-android-compiler:2.46")

    //Pager
    implementation("com.google.accompanist:accompanist-pager:0.34.0")

    implementation("androidx.compose.material:material-icons-core:1.6.2")
    implementation("androidx.compose.material:material-icons-extended:1.6.2")

    //Lottie
    implementation("com.airbnb.android:lottie-compose:4.2.0")
    implementation("com.airbnb.android:lottie:4.2.0")

    // Retrofit for network calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutine support for Retrofit
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp3Version")
    //Splash Api
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.13-rc")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation ("androidx.datastore:datastore-preferences:1.1.1")

    implementation ("com.airbnb.android:lottie-compose:6.4.0")

    implementation ("com.google.accompanist:accompanist-flowlayout:0.21.1-beta")

}

kapt {
    correctErrorTypes = true
}
