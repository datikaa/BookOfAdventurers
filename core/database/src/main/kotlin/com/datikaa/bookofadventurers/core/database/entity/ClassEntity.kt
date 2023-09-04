package com.datikaa.bookofadventurers.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClassEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val selectableSkillCount: Int,
)
