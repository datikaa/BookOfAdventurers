package com.datikaa.charlatan.core.database.di

import androidx.room.Room
import com.datikaa.charlatan.core.database.CmmDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseKoinModule = module {
    single<CmmDatabase> {
        Room
            .databaseBuilder(
                androidApplication(),
                CmmDatabase::class.java, "charlatan-character-management-database"
            )
            .fallbackToDestructiveMigrationFrom(1)
            .build()
    }
}