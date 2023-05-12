package com.datikaa.charlatan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class, CharAttribute::class], version = 1)
abstract class CmmDatabase : RoomDatabase() {
    abstract fun attributesDao(): AttributeDao
    abstract fun characterDao(): CharacterDao
}