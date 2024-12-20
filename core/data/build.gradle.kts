import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
    id("bookofadventurers.kmm.compose")
    id("bookofadventurers.kmm.library")
}

kotlin {
    configureAppleFrameworks {
        baseName = "data"
        isStatic = true
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.core.database)
            implementation(projects.core.domain)

            implementation(compose.runtime)
            implementation(libs.koin.core)
        }
        iosMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers.core.data"
}
