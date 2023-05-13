package com.datikaa.charlatan.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val characterId: Int,
    val name: String,
)
