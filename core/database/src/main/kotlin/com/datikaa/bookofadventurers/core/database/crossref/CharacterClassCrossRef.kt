package com.datikaa.bookofadventurers.core.database.crossref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["characterId", "classId"])
data class CharacterClassCrossRef(
    @ColumnInfo(index = true) val characterId: Long,
    @ColumnInfo(index = true) val classId: Long
)
