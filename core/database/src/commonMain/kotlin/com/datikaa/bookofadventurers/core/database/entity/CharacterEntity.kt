package com.datikaa.bookofadventurers.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val level: Int,
)
