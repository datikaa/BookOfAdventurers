package com.datikaa.bookofadventurers.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datikaa.bookofadventurers.core.database.crossref.BackgroundSkillProficiencyCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterClassCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterSelectedClassModifierCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.ClassSavingThrowCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.ClassSkillProficiencyCrossRef
import com.datikaa.bookofadventurers.core.database.dao.AbilityDao
import com.datikaa.bookofadventurers.core.database.dao.BackgroundDao
import com.datikaa.bookofadventurers.core.database.dao.CharacterDao
import com.datikaa.bookofadventurers.core.database.dao.ClassDao
import com.datikaa.bookofadventurers.core.database.dao.ModifierDao
import com.datikaa.bookofadventurers.core.database.entity.AbilityEntity
import com.datikaa.bookofadventurers.core.database.entity.BackgroundEntity
import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.database.entity.ClassEntity
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity

@Database(
    entities = [
        BackgroundEntity::class,
        CharacterEntity::class,
        ClassEntity::class,
        AbilityEntity::class,
        ModifierEntity::class,
        BackgroundSkillProficiencyCrossRef::class,
        ClassSavingThrowCrossRef::class,
        ClassSkillProficiencyCrossRef::class,
        CharacterClassCrossRef::class,
        CharacterModifierCrossRef::class,
        CharacterSelectedClassModifierCrossRef::class,
    ], version = 11
)
abstract class BoaDatabase : RoomDatabase() {
    abstract fun abilityDao(): AbilityDao
    abstract fun backgroundDao(): BackgroundDao
    abstract fun characterDao(): CharacterDao
    abstract fun classDao(): ClassDao
    abstract fun modifierDao(): ModifierDao
}
