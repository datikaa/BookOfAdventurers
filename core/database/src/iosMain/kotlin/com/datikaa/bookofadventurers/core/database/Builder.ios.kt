package com.datikaa.bookofadventurers.core.database

import androidx.room.Room
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import platform.Foundation.NSHomeDirectory

actual fun Module.roomDatabaseFactory(): KoinDefinition<BoaDatabase> = factory {
    val dbFilePath = NSHomeDirectory() + "/book-of-adventurers-database.db"
    Room.databaseBuilder<BoaDatabase>(
        name = dbFilePath,
        factory = { BoaDatabase::class.instantiateImpl() }
    )
        .fallbackToDestructiveMigration(false)
        .build()
}
