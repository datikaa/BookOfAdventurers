package com.datikaa.bookofadventurers.core.analytics

interface Platform {
    val isDebug: Boolean
}

expect fun getPlatform(): Platform