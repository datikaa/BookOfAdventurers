package com.datikaa.bookofadventurers.core.analytics.di

import com.datikaa.bookofadventurers.core.analytics.AnalyticsHelper
import org.koin.dsl.module

expect fun analyticsHelper(): AnalyticsHelper

val analyticsModule = module {
    single<AnalyticsHelper> { analyticsHelper() }
}
