@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("charlatan.android.application")
    id("charlatan.android.application.compose")
}

android {
    namespace = "com.datikaa.charlatan"

    defaultConfig {
        applicationId = "com.datikaa.charlatan"
        versionCode = 1
        versionName = "0.0.1"

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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:design"))
    implementation(project(":feature:character"))
    implementation(project(":feature:overview"))

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