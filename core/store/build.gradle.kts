import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.moko.resource)
    id("bookofadventurers.kmm.library")
}

kotlin {
    configureAppleFrameworks {
        baseName = "store"
        isStatic = true
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.core.jsonResources)

            implementation(libs.koin.core)
            implementation(libs.kstore)
            implementation(libs.kstore.file)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.moko.resource)
        }

        iosMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers.core.store"
}

multiplatformResources {
    resourcesPackage.set("com.datikaa.bookofadventurers.core.store")
    resourcesClassName.set("Res")
}
