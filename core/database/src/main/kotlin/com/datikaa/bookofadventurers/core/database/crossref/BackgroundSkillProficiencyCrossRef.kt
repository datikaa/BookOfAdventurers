package com.datikaa.bookofadventurers.core.database.crossref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["backgroundId", "modifierId"])
data class BackgroundSkillProficiencyCrossRef(
    @ColumnInfo(index = true) val backgroundId: Long,
    @ColumnInfo(index = true) val modifierId: Long
)
