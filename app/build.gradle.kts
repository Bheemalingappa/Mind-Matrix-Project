plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {

    namespace = "com.example.gramayatri"

    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.gramayatri"

        minSdk = 26

        targetSdk = 34

        versionCode = 1

        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
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
    }

    composeOptions {

        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {

        resources {

            excludes +=
                "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.google.firebase:firebase-messaging-ktx:24.0.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.google.maps.android:maps-compose:4.3.3")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    implementation("com.google.maps.android:maps-compose:4.3.3")

implementation("com.google.android.gms:play-services-maps:18.2.0")

implementation("com.google.android.gms:play-services-location:21.2.0")

implementation("androidx.compose.animation:animation")

implementation("androidx.compose.material:material-icons-extended")
    // Media3
    implementation(
        "androidx.media3:media3-common:1.3.1"
    )



    // Google Maps
    implementation(
        "com.google.maps.android:maps-compose:2.11.4"
    )

    implementation(
        "com.google.android.gms:play-services-maps:18.2.0"
    )

    implementation(
        "com.google.android.gms:play-services-location:21.3.0"
    )

    // Core Android
    implementation(
        "androidx.core:core-ktx:1.12.0"
    )

    implementation(
        "androidx.lifecycle:lifecycle-runtime-ktx:2.7.0"
    )

    implementation(
        "androidx.activity:activity-compose:1.8.2"
    )

    // Compose BOM
    implementation(
        platform(
            "androidx.compose:compose-bom:2024.02.00"
        )
    )

    implementation(
        "androidx.compose.ui:ui"
    )

    implementation(
        "androidx.compose.ui:ui-graphics"
    )

    implementation(
        "androidx.compose.ui:ui-tooling-preview"
    )

    implementation(
        "androidx.compose.material3:material3"
    )

    implementation(
        "androidx.compose.material:material-icons-extended"
    )

    debugImplementation(
        "androidx.compose.ui:ui-tooling"
    )

    debugImplementation(
        "androidx.compose.ui:ui-test-manifest"
    )

    // Navigation
    implementation(
        "androidx.navigation:navigation-compose:2.7.7"
    )

    // ViewModel
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0"
    )

    // Firebase
    implementation(
        platform(
            "com.google.firebase:firebase-bom:33.1.2"
        )
    )

    implementation(
        "com.google.firebase:firebase-database-ktx"
    )

    implementation(
        "com.google.firebase:firebase-auth-ktx"
    )

    implementation(
        "com.google.firebase:firebase-messaging-ktx"
    )

    // Room Database
    implementation(
        "androidx.room:room-runtime:2.6.1"
    )

    implementation(
        "androidx.room:room-ktx:2.6.1"
    )

    kapt(
        "androidx.room:room-compiler:2.6.1"
    )

    // Hilt
    implementation(
        "com.google.dagger:hilt-android:2.51"
    )

    kapt(
        "com.google.dagger:hilt-compiler:2.51"
    )

    // Gemini AI
    implementation(
        "com.google.ai.client.generativeai:generativeai:0.9.0"
    )

    // Unit Testing
    testImplementation(
        "junit:junit:4.13.2"
    )

    // Android Testing
    androidTestImplementation(
        "androidx.test.ext:junit:1.1.5"
    )

    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.5.1"
    )

    androidTestImplementation(
        platform(
            "androidx.compose:compose-bom:2024.02.00"
        )
    )

    androidTestImplementation(
        "androidx.compose.ui:ui-test-junit4"
    )
}