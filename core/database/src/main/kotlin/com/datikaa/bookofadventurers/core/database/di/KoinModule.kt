package com.datikaa.bookofadventurers.core.database.di

import androidx.room.Room
import com.datikaa.bookofadventurers.core.database.BoaDatabase
import com.datikaa.bookofadventurers.core.database.prefill.PreloadDb
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseKoinModule = module {
    single<BoaDatabase> {
        Room
            .databaseBuilder(
                androidApplication(),
                BoaDatabase::class.java, "book-of-adventurers-database"
            )
            .fallbackToDestructiveMigration()
            .addCallback(PreloadDb(androidContext()))
            .build()
    }

    factory { get<BoaDatabase>().abilityDao() }
    factory { get<BoaDatabase>().classDao() }
    factory { get<BoaDatabase>().characterDao() }
    factory { get<BoaDatabase>().modifierDao() }
}
