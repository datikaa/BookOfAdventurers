package com.datikaa.bookofadventurers.core.analytics.di

import com.datikaa.bookofadventurers.core.analytics.AnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.NoOpAnalyticsHelper
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf

actual fun Module.analyticsHelper() {
   singleOf<AnalyticsHelper>(::NoOpAnalyticsHelper)
}
