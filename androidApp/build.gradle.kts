plugins {
    id("bookofadventurers.android.application")
    id("bookofadventurers.kmm.compose")
}

android {
    namespace = "com.datikaa.bookofadventurers"

    defaultConfig {
        applicationId = "com.datikaa.bookofadventurers"
        versionCode = 2
        versionName = "v0.7.2"

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
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = " (Debug)"
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.app)

    implementation(libs.activity.compose)
    implementation(libs.koin.android)
    testImplementation(libs.junit4)
    testImplementation(libs.koin.test)
    testImplementation(libs.kotest.assertions.core)
}
