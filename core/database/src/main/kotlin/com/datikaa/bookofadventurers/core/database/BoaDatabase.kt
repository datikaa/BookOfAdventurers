package com.datikaa.bookofadventurers.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datikaa.bookofadventurers.core.database.crossref.CharacterClassCrossRef
import com.datikaa.bookofadventurers.core.database.dao.AbilityDao
import com.datikaa.bookofadventurers.core.database.entity.AbilityEntity
import com.datikaa.bookofadventurers.core.database.dao.CharacterDao
import com.datikaa.bookofadventurers.core.database.dao.ModifierDao
import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.ClassModifierCrossRef
import com.datikaa.bookofadventurers.core.database.dao.ClassDao
import com.datikaa.bookofadventurers.core.database.entity.ClassEntity
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity

@Database(
    entities = [
        CharacterEntity::class,
        ClassEntity::class,
        AbilityEntity::class,
        ModifierEntity::class,
        ClassModifierCrossRef::class,
        CharacterClassCrossRef::class,
        CharacterModifierCrossRef::class,
    ], version = 7
)
abstract class BoaDatabase : RoomDatabase() {
    abstract fun abilityDao(): AbilityDao
    abstract fun characterDao(): CharacterDao
    abstract fun classDao(): ClassDao
    abstract fun modifierDao(): ModifierDao
}
