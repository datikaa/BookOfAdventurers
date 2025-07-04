package com.datikaa.bookofadventurers

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {

    val jvmTarget = JvmTarget.JVM_17
    val javaVersion = JavaVersion.VERSION_17
    const val compileSdk = 36
    const val targetSdk = 36
    const val minSdk = 24
}
