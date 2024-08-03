package com.datikaa.bookofadventurers.core.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import platform.Foundation.NSHomeDirectory

actual fun Module.roomDatabaseFactory(): KoinDefinition<BoaDatabase> = factory {
    val dbFilePath = NSHomeDirectory() + "/book-of-adventurers-database.db"
    Room.databaseBuilder<BoaDatabase>(
        name = dbFilePath,
        factory = { BoaDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .fallbackToDestructiveMigration(false)
        .build()
}
