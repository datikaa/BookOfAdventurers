import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.activity.compose)
        }
        commonMain.dependencies {
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
    compileSdk = 34

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
            resources.srcDirs("src/commonMain/resources")
        }
    }

    defaultConfig {
        applicationId = "com.datikaa.bookofadventurers"
        versionCode = 2
        versionName = "v0.5.0"

        minSdk = 24
        targetSdk = 34

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
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    dependencies {
        debugImplementation(compose.uiTooling)

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
