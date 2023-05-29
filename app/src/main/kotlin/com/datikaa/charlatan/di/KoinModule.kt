package com.datikaa.charlatan.di

import com.datikaa.charlatan.core.database.di.databaseKoinModule
import com.datikaa.charlatan.feature.editor.di.characterKoinModule
import com.datikaa.charlatan.feature.overview.di.overviewKoinModule
import com.datikaa.core.data.di.dataModule
import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule,
        characterKoinModule,
        databaseKoinModule,
        overviewKoinModule,
    )
}