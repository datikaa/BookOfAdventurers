package com.datikaa.bookofadventurers.core.analytics

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Global key used to obtain access to the AnalyticsHelper through a CompositionLocal.
 */
val LocalAnalyticsHelper = staticCompositionLocalOf<AnalyticsHelper> {
    NoOpAnalyticsHelper()
}
