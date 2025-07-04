plugins {
    id("bookofadventurers.kmm.application")
    id("bookofadventurers.kmm.compose")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.activity.compose)
            implementation(libs.koin.android)
        }
        androidUnitTest.dependencies {
            implementation(libs.junit4)
            implementation(libs.koin.test)
            implementation(libs.kotest.assertions.core)
        }
        commonMain.dependencies {
            implementation(projects.app)
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.koin.test)
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers"

    defaultConfig {
        applicationId = "com.datikaa.bookofadventurers"
        versionCode = 2
        versionName = "v0.7.1"

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
