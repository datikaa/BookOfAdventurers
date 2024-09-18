package com.datikaa.bookofadventurers.core.analytics

import android.util.Log

private const val TAG = "AndroidAnalyticsHelper"

/**
 * An implementation of AnalyticsHelper just writes the events to logcat. Used in builds where no
 * analytics events should be sent to a backend.
 */
class AndroidAnalyticsHelper : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) {
        Log.d(TAG, "Received analytics event: $event")
    }

    override fun recordNonFatalException(throwable: Throwable) {
        Log.e(TAG, "Non fatal exception", throwable)
    }
}
