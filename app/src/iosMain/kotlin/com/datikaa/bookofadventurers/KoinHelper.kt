package com.datikaa.bookofadventurers

import com.datikaa.bookofadventurers.di.appModule
import com.datikaa.bookofadventurers.di.platformModule
import org.koin.core.context.startKoin


fun initKoin() {
    startKoin {
        modules(appModule, platformModule)
    }
}
