package com.datikaa.charlatan.feature.charcore

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val characterId: Int,
    val name: String,
)

@Entity
data class CharAttribute(
    @PrimaryKey(autoGenerate = true) val charAttributeId: Int,
    val name: String,
    val value: Int,
)
