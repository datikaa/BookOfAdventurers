package com.datikaa.bookofadventurers.core.analytics

/**
 * Implementation of AnalyticsHelper which does nothing. Useful for tests and previews.
 */
class NoOpAnalyticsHelper : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) = Unit
    override fun recordNonFatalException(throwable: Throwable) = Unit
}
