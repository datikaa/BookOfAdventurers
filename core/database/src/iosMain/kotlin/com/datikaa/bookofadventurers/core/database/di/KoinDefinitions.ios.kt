package com.datikaa.bookofadventurers.core.database.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.datikaa.bookofadventurers.core.database.BoaDatabase
import com.datikaa.bookofadventurers.core.database.instantiateImpl
import com.datikaa.bookofadventurers.core.database.prefill.IosJsonResourceFunctions
import com.datikaa.bookofadventurers.core.database.prefill.JsonResourceFunctions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import platform.Foundation.NSHomeDirectory

actual fun Module.roomDatabaseSingle(): KoinDefinition<BoaDatabase> = factory {
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

actual fun Module.jsonResourceFunctionsFactory(): KoinDefinition<JsonResourceFunctions> = factory {
    IosJsonResourceFunctions()
}
