plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val compose_version:String by rootProject.extra

android {
    namespace = "io.aircore.panel.mediaviewsample"
    compileSdk = 33

    defaultConfig {
        applicationId = "io.aircore.panel.mediaviewsample"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "2.0.1"

        buildConfigField("String", "PUBLISHABLE_API_KEY", "\"Replace with your publishable API key\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion  = compose_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Media Panel SDK
    implementation("io.aircore.panel:media-view:2.0.1")

    // Android dependencies
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}