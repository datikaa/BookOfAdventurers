package com.datikaa.boa.core.analytics

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val analyticsModule = module {
    singleOf<AnalyticsHelper>(::StubAnalyticsHelper)
}
