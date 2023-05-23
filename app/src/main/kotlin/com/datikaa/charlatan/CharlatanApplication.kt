package com.datikaa.charlatan

import android.app.Application
import com.datikaa.charlatan.core.database.di.databaseKoinModule
import com.datikaa.charlatan.feature.character.di.characterKoinModule
import com.datikaa.charlatan.feature.overview.di.overviewKoinModule
import com.datikaa.core.data.di.dataModule
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
                dataModule,
                characterKoinModule,
                databaseKoinModule,
                overviewKoinModule,
            )
        }
    }
}