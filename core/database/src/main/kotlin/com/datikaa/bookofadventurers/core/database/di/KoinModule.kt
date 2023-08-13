package com.datikaa.bookofadventurers.core.database.di

import androidx.room.Room
import com.datikaa.bookofadventurers.core.database.BoaDatabase
import com.datikaa.bookofadventurers.core.database.prefill.PreloadBasicModifiers
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
                .fallbackToDestructiveMigrationFrom(5)
                .addCallback(PreloadBasicModifiers(androidContext()))
                .build()
    }

    factory { get<BoaDatabase>().abilityDao() }

    factory { get<BoaDatabase>().characterDao() }

    factory { get<BoaDatabase>().modifierDao() }
}
