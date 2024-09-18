package com.datikaa.bookofadventurers

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {

    val jvmTarget = JvmTarget.JVM_17
    val javaVersion = JavaVersion.VERSION_17
    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 24
}
