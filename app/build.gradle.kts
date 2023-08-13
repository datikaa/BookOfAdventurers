@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("bookofadventurers.android.application")
    id("bookofadventurers.android.application.compose")
    id("bookofadventurers.android.application.firebase")
}

android {
    namespace = "com.datikaa.bookofadventurers"

    defaultConfig {
        applicationId = "com.datikaa.bookofadventurers"
        versionCode = 2
        versionName = "0.0.3"

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
    implementation(project(":feature:editor"))
    implementation(project(":feature:launcher"))
    implementation(project(":feature:modifier"))
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
