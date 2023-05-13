package com.datikaa.charlatan.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AttributeEntity(
    @PrimaryKey(autoGenerate = true) val charAttributeId: Int,
    val name: String,
    val value: Int,
)