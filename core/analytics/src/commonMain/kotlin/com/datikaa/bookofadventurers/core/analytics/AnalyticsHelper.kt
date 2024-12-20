package com.datikaa.bookofadventurers.core.analytics

/**
 * Interface for logging analytics events. See `FirebaseAnalyticsHelper` and
 * `StubAnalyticsHelper` for implementations.
 */
interface AnalyticsHelper {
    fun logEvent(event: AnalyticsEvent)
    fun recordNonFatalException(throwable: Throwable)
}
