package com.datikaa.charlatan.core.database.crossref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["characterId", "modifierId"])
data class CharacterModifierCrossRef(
    @ColumnInfo(index = true) val characterId: Long,
    @ColumnInfo(index = true) val modifierId: Long
)