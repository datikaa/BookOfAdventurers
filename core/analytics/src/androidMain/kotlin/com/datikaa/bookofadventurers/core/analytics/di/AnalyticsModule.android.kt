package com.datikaa.bookofadventurers.core.analytics.di

import com.datikaa.bookofadventurers.core.analytics.AnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.AndroidAnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.getPlatform

actual fun analyticsHelper(): AnalyticsHelper {
    return if (getPlatform().isDebug) {
        AndroidAnalyticsHelper()
    } else {
        AndroidAnalyticsHelper()
    }
}
