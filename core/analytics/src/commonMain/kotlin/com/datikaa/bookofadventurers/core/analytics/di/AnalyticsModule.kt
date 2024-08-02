package com.datikaa.bookofadventurers.core.analytics.di

import com.datikaa.bookofadventurers.core.analytics.AnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.StubAnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.getPlatform
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    if (getPlatform().isDebug) {
        singleOf(::StubAnalyticsHelper) bind AnalyticsHelper::class
    } else {
        singleOf(::StubAnalyticsHelper) bind AnalyticsHelper::class
    }
}
