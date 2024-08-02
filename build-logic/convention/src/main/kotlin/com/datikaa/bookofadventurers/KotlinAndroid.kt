package com.datikaa.bookofadventurers

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = Config.compileSdk

        defaultConfig {
            minSdk = Config.minSdk
        }

        compileOptions {
            sourceCompatibility = Config.javaVersion
            targetCompatibility = Config.javaVersion
        }
    }

//    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
//    tasks.withType<KotlinCompile>().configureEach {
//        compilerOptions {
//
//        }
//        kotlinOptions {
//            // Set JVM target to 17
//            jvmTarget = JavaVersion.VERSION_17.toString()
//            val warningsAsErrors: String? by project
//            allWarningsAsErrors = warningsAsErrors.toBoolean()
//            freeCompilerArgs = freeCompilerArgs + listOf(
//                "-opt-in=kotlin.RequiresOptIn",
//                // Enable experimental coroutines APIs, including Flow
////                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
////                "-opt-in=kotlinx.coroutines.FlowPreview",
//            )
//        }
//    }
}
