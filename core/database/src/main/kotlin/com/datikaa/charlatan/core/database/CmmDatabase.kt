package com.datikaa.charlatan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datikaa.charlatan.core.database.dao.AbilityDao
import com.datikaa.charlatan.core.database.entity.AbilityEntity
import com.datikaa.charlatan.core.database.dao.CharacterDao
import com.datikaa.charlatan.core.database.dao.ModifierDao
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.charlatan.core.database.entity.ModifierEntity

@Database(
    entities = [
        CharacterEntity::class,
        AbilityEntity::class,
        ModifierEntity::class,
        CharacterModifierCrossRef::class,
    ], version = 6
)
abstract class CmmDatabase : RoomDatabase() {
    abstract fun abilityDao(): AbilityDao
    abstract fun characterDao(): CharacterDao

    abstract fun modifierDao(): ModifierDao
}