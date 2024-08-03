import com.datikaa.bookofadventurers.configureAppleFrameworks
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
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
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata")
        }
        androidMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.android)
            implementation(libs.room.runtime)
        }
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.sqlite.bundled)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.moko.resource)
            api(libs.room.runtime)
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
    add("kspCommonMainMetadata", libs.room.compiler)
}

multiplatformResources {
    resourcesPackage.set("com.datikaa.bookofadventurers")
    resourcesClassName.set("Res")
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata" ) {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
