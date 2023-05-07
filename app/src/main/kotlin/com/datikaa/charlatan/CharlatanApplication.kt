package com.datikaa.charlatan

import android.app.Application
import com.datikaa.charlatan.feature.charcore.di.charCoreKoinModule
import com.datikaa.charlatan.feature.overview.di.overviewKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CharlatanApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@CharlatanApplication)
            // Load modules
            modules(
                overviewKoinModule,
                charCoreKoinModule,
            )
        }
    }
}