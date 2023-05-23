package com.datikaa.charlatan.core.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val level: Int,
)

@Entity
data class CharacterWithAttributes(
    @Embedded
    val characterEntity: CharacterEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val attributes: List<AttributeEntity>
)

