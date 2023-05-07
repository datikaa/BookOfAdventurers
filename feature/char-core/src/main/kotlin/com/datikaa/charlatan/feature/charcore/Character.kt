package com.datikaa.charlatan.feature.charcore

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Character(
    @PrimaryKey val characterId: Int,
    val name: String,
)

@Entity
data class CharAttribute(
    @PrimaryKey val charAttributeId: Int,
    val name: String,
    val value: Int,
)

@Entity
data class CharacterWithAttributes(
    @Embedded val character: Character,
    @Relation(
        parentColumn = "characterId",
        entityColumn = "charAttributeId"
    )
    val attributes: List<CharAttribute>
)
