package com.datikaa.bookofadventurers.core.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.datikaa.bookofadventurers.core.database.entity.BackgroundEntity
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity

data class BackgroundWithModifiers(
    @Embedded
    val backgroundEntity: BackgroundEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = ModifierEntity::class,
        associateBy = Junction(
            value = BackgroundWithModifiers::class,
            parentColumn = "classId",
            entityColumn = "modifierId",
        )
    )
    val skillProficiencyModifierEntities: List<ModifierEntity>,
)
