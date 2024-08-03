package com.datikaa.bookofadventurers.core.analytics.di

import com.datikaa.bookofadventurers.core.analytics.NoOpAnalyticsHelper
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect fun Module.analyticsHelper(): Unit

val analyticsModule = module {
    analyticsHelper()
}
