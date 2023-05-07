package com.datikaa.charlatan.feature.charcore

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class, CharAttribute::class], version = 1)
abstract class CmmDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}