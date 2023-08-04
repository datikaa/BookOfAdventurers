package com.datikaa.boa.core.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    single<FirebaseAnalytics> { Firebase.analytics }
    singleOf(::FirebaseAnalyticsHelper) bind AnalyticsHelper::class
}
