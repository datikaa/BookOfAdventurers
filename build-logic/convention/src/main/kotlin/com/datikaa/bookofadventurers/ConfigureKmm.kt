package com.datikaa.bookofadventurers

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Configure base Kotlin with Kmm options
 */
@OptIn(ExperimentalKotlinGradlePluginApi::class)
internal fun Project.configureKmm() {
    extensions.configure<KotlinMultiplatformExtension> {
        androidTarget {
            compilerOptions {
                jvmTarget.set(Config.jvmTarget)
            }
        }
    }
}
