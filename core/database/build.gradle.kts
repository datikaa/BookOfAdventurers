import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.moko.resource)
    id("bookofadventurers.kmm.library")
}

kotlin {
    configureAppleFrameworks {
        baseName = "database"
        isStatic = true
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.sqlite.bundled)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.moko.resource)
            implementation(libs.room.runtime)
        }
        commonTest.dependencies {
            implementation(libs.moko.resource.test)
        }
        iosMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers.core.database"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
}

multiplatformResources {
    resourcesPackage.set("com.datikaa.bookofadventurers")
    resourcesClassName.set("Res")
}
