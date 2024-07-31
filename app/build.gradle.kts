@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.compose.compiler)
    id("bookofadventurers.android.application")
    id("bookofadventurers.android.application.firebase")
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

dependencies {

    implementation(project(":core:analytics"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:design"))
    implementation(project(":feature:backgrounds"))
    implementation(project(":feature:editor"))
    implementation(project(":feature:launcher"))
    implementation(project(":feature:modifier"))
    implementation(project(":feature:overview"))
    implementation(project(":feature:wizard"))

    implementation(libs.activity.compose)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)


    implementation(libs.androidx.navigation.navigationCompose)

    implementation(libs.koin.android)

    testImplementation(libs.junit4)
    testImplementation(libs.koin.test)
    testImplementation(libs.kotest.assertions.core)
}
