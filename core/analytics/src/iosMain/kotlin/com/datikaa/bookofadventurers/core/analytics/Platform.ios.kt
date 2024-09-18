package com.datikaa.bookofadventurers.core.analytics

import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.Platform as iOSPlatform

@OptIn(ExperimentalNativeApi::class)
object IosPlatform : Platform {
    override val isDebug: Boolean = iOSPlatform.isDebugBinary
}

actual fun getPlatform(): Platform = IosPlatform