package com.datikaa.boa

import android.app.Application
import com.datikaa.boa.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BookOfAdventurersApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@BookOfAdventurersApplication)
            // Load modules
            modules(appModule)
        }
    }
}
