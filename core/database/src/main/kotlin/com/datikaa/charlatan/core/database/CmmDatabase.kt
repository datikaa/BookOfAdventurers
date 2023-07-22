package com.datikaa.charlatan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datikaa.charlatan.core.database.dao.AttributeDao
import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.dao.CharacterDao
import com.datikaa.charlatan.core.database.dao.ModifierDao
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.entity.CharacterModifierCrossRef
import com.datikaa.charlatan.core.database.entity.ModifierEntity

@Database(
    entities = [
        CharacterEntity::class,
        AttributeEntity::class,
        ModifierEntity::class,
        CharacterModifierCrossRef::class,
    ], version = 5
)
abstract class CmmDatabase : RoomDatabase() {
    abstract fun attributesDao(): AttributeDao
    abstract fun characterDao(): CharacterDao

    abstract fun modifierDao(): ModifierDao
}