package com.datikaa.bookofadventurers.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BackgroundEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val featureTitle: String,
    val featureDescription: String,
    val suggestedCharacteristics: String,
)
