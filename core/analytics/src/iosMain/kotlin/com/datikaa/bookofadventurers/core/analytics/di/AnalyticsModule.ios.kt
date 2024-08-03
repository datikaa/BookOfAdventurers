package com.datikaa.bookofadventurers.core.analytics.di

import com.datikaa.bookofadventurers.core.analytics.AnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.NoOpAnalyticsHelper

actual fun analyticsHelper(): AnalyticsHelper = NoOpAnalyticsHelper()
