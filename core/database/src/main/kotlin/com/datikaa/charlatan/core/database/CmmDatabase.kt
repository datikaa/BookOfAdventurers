package com.datikaa.charlatan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datikaa.charlatan.core.database.dao.AttributeDao
import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.dao.CharacterDao
import com.datikaa.charlatan.core.database.entity.CharacterEntity

@Database(entities = [CharacterEntity::class, AttributeEntity::class], version = 1)
abstract class CmmDatabase : RoomDatabase() {
    abstract fun attributesDao(): AttributeDao
    abstract fun characterDao(): CharacterDao
}