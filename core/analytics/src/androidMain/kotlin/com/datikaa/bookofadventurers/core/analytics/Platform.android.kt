package com.datikaa.bookofadventurers.core.analytics

object AndroidPlatform : Platform {
    override val isDebug: Boolean = BuildConfig.DEBUG
}

actual fun getPlatform(): Platform = AndroidPlatform