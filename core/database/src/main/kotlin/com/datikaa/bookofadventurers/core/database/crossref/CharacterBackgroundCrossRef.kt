package com.datikaa.bookofadventurers.core.database.crossref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["characterId", "backgroundId"])
data class CharacterBackgroundCrossRef(
    @ColumnInfo(index = true) val characterId: Long,
    @ColumnInfo(index = true) val backgroundId: Long
)
