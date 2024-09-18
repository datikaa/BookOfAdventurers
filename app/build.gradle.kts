import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
    alias(libs.plugins.moko.resource)
    id("bookofadventurers.kmm.application")
    id("bookofadventurers.kmm.compose")
}

kotlin {
    configureAppleFrameworks {
        baseName = "ComposeApp"
        isStatic = true
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.activity.compose)
            implementation(libs.koin.android)
        }
        androidUnitTest.dependencies {
            implementation(libs.junit4)
            implementation(libs.koin.test)
            implementation(libs.kotest.assertions.core)
        }
        commonMain.dependencies {
            implementation(projects.core.analytics)
            implementation(projects.core.data)
            implementation(projects.core.database)
            implementation(projects.core.design)
            implementation(projects.feature.backgrounds)
            implementation(projects.feature.editor)
            implementation(projects.feature.launcher)
            implementation(projects.feature.modifier)
            implementation(projects.feature.overview)
            implementation(projects.feature.wizard)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.navigation)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
        }
        commonTest.dependencies {
            implementation(libs.koin.test)
        }
        iosMain.dependencies {
            // ios deps
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers"

    defaultConfig {
        applicationId = "com.datikaa.bookofadventurers"
        versionCode = 2
        versionName = "v0.5.0"

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
