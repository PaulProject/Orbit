plugins {
    kotlin("kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.orbit.country"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":design"))

    /*Core*/
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    /*DI*/
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")

    /*Serialize*/
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    implementation("com.squareup.moshi:moshi-adapters:1.15.0")

    /*Network*/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    /*Compose*/
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    /*Image*/
    implementation("io.coil-kt:coil-compose:2.4.0")

    /*Navigation*/
    implementation("com.github.terrakok:cicerone:7.1")

    /*MVI*/
    implementation("org.orbit-mvi:orbit-core:6.0.0")
    implementation("org.orbit-mvi:orbit-viewmodel:6.0.0")
    implementation("org.orbit-mvi:orbit-compose:6.0.0")

    /*Test*/
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.7")
    testImplementation("org.orbit-mvi:orbit-test:6.0.0")
    testImplementation("org.amshove.kluent:kluent-android:1.73")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}