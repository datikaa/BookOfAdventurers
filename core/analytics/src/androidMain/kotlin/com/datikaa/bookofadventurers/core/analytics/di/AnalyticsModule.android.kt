package com.datikaa.bookofadventurers.core.analytics.di

import com.datikaa.bookofadventurers.core.analytics.AnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.AndroidAnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.getPlatform
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf

actual fun Module.analyticsHelper() {
    if (getPlatform().isDebug) {
        singleOf<AnalyticsHelper>(::AndroidAnalyticsHelper)
    } else {
        singleOf<AnalyticsHelper>(::AndroidAnalyticsHelper)
    }
}
