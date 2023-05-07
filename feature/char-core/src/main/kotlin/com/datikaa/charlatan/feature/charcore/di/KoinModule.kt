package com.datikaa.charlatan.feature.charcore.di

import androidx.room.Room
import com.datikaa.charlatan.feature.charcore.CmmDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val charCoreKoinModule = module {
    single<CmmDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            CmmDatabase::class.java, "charlatan-character-management-database"
        ).build()
    }
}