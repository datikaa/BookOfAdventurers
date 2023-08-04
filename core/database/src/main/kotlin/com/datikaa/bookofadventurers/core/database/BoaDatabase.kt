package com.datikaa.bookofadventurers.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datikaa.bookofadventurers.core.database.dao.AbilityDao
import com.datikaa.bookofadventurers.core.database.entity.AbilityEntity
import com.datikaa.bookofadventurers.core.database.dao.CharacterDao
import com.datikaa.bookofadventurers.core.database.dao.ModifierDao
import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity

@Database(
    entities = [
        CharacterEntity::class,
        AbilityEntity::class,
        ModifierEntity::class,
        CharacterModifierCrossRef::class,
    ], version = 6
)
abstract class BoaDatabase : RoomDatabase() {
    abstract fun abilityDao(): AbilityDao
    abstract fun characterDao(): CharacterDao

    abstract fun modifierDao(): ModifierDao
}
