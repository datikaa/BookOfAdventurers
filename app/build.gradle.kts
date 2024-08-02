import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
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
        }
        commonMain.dependencies {
            implementation(projects.core.analytics)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.junit4)
            implementation(libs.koin.test)
            implementation(libs.kotest.assertions.core)
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

    dependencies {
        debugImplementation(compose.uiTooling)

        implementation(project(":core:data"))
        implementation(project(":core:database"))
        implementation(project(":core:design"))
        implementation(project(":feature:backgrounds"))
        implementation(project(":feature:editor"))
        implementation(project(":feature:launcher"))
        implementation(project(":feature:modifier"))
        implementation(project(":feature:overview"))
        implementation(project(":feature:wizard"))

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
}
