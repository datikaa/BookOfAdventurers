package com.datikaa.bookofadventurers.core.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.datikaa.bookofadventurers.core.database.crossref.CharacterBackgroundCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterClassCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterSelectedClassModifierCrossRef
import com.datikaa.bookofadventurers.core.database.entity.AbilityEntity
import com.datikaa.bookofadventurers.core.database.entity.BackgroundEntity
import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.database.entity.ClassEntity
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity

data class CharacterWithAbilitiesAndModifiers(
    @Embedded
    val characterEntity: CharacterEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val abilityEntities: List<AbilityEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = BackgroundEntity::class,
        associateBy = Junction(
            value = CharacterBackgroundCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "backgroundId",
        )
    )
    val backgroundWithModifiersEntities: List<BackgroundWithModifiers>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = ClassEntity::class,
        associateBy = Junction(
            value = CharacterClassCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "classId",
        )
    )
    val classWithModifiersEntities: List<ClassWithModifiers>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = ModifierEntity::class,
        associateBy = Junction(
            value = CharacterSelectedClassModifierCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "modifierId",
        )
    )
    val selectedClassSkillModifierEntities: List<ModifierEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = ModifierEntity::class,
        associateBy = Junction(
            value = CharacterModifierCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "modifierId",
        )
    )
    val modifierEntities: List<ModifierEntity>,
)