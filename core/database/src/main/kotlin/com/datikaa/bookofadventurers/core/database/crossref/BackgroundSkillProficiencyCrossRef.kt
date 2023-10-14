package com.datikaa.bookofadventurers.core.database.crossref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["backgroundsId", "modifierId"])
data class BackgroundSkillProficiencyCrossRef(
    @ColumnInfo(index = true) val backgroundsId: Long,
    @ColumnInfo(index = true) val modifierId: Long
)
