package com.datikaa.bookofadventurers.core.database

import androidx.room.Room
import com.datikaa.bookofadventurers.core.database.prefill.PreloadDb
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

actual fun Module.roomDatabaseFactory(): KoinDefinition<BoaDatabase> = factory {
    val dbFile = androidApplication().getDatabasePath("book-of-adventurers-database.db")
    Room.databaseBuilder<BoaDatabase>(
        context = androidApplication(),
        name = dbFile.absolutePath
    )
        .fallbackToDestructiveMigration(false)
        .addCallback(PreloadDb(androidContext()))
        .build()
}
