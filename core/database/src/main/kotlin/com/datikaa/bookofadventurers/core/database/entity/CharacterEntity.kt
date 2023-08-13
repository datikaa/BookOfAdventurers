package com.datikaa.bookofadventurers.core.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.datikaa.bookofadventurers.core.database.crossref.CharacterClassCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterModifierCrossRef

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val level: Int,
)

@Entity
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
            value = CharacterModifierCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "modifierId",
        )
    )
    val modifierEntities: List<ModifierEntity>,
)
