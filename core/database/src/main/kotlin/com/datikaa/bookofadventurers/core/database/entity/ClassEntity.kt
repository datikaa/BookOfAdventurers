package com.datikaa.bookofadventurers.core.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.datikaa.bookofadventurers.core.database.crossref.ClassModifierCrossRef

@Entity
data class ClassEntity(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val name: String,
)

@Entity
data class ClassWithModifiers(
        @Embedded
        val classEntity: ClassEntity,
        @Relation(
                parentColumn = "id",
                entityColumn = "id",
                entity = ModifierEntity::class,
                associateBy = Junction(
                        value = ClassModifierCrossRef::class,
                        parentColumn = "classId",
                        entityColumn = "modifierId",
                )
        )
        val modifierEntities: List<ModifierEntity>,
)