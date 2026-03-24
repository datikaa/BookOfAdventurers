import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.moko.resource)
    id("bookofadventurers.kmm.library")
}

kotlin {
    configureAppleFrameworks {
        baseName = "jsonresources"
        isStatic = true
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kstore)
            implementation(libs.kstore.file)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.moko.resource)
        }

        iosMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers.core.jsonresources"
}
