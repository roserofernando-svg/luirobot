plugins {
    id("com.android.application")
    kotlin("android")
}

import java.util.Properties

android {
    namespace = "com.fer.louierobot"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fer.louierobot"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val props = Properties().apply {
            val f = project.rootProject.file("local.properties")
            if (f.exists()) f.inputStream().use { load(it) }
        }
        val openaiKey = props.getProperty("OPENAI_API_KEY") ?: ""
        buildConfigField("String", "OPENAI_API_KEY", ""$openaiKey"")
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

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.8" }
    kotlinOptions { jvmTarget = "17" }
    packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.6.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.3")

    implementation("androidx.compose.material3:material3:1.2.1")

    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okio:okio:3.6.0")
    implementation("org.json:json:20240303")
}
