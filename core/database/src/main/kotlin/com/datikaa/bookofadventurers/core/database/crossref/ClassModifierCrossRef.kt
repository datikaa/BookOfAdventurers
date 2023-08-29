package com.datikaa.bookofadventurers.core.database.crossref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["classId", "modifierId"])
data class ClassModifierCrossRef(
    @ColumnInfo(index = true) val classId: Long,
    @ColumnInfo(index = true) val modifierId: Long
)
