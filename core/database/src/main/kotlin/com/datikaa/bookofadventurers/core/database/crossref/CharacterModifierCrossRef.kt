package com.datikaa.bookofadventurers.core.database.crossref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["characterId", "modifierId"])
data class CharacterModifierCrossRef(
    @ColumnInfo(index = true) val characterId: Long,
    @ColumnInfo(index = true) val modifierId: Long
)

@Entity(primaryKeys = ["characterId", "modifierId"])
data class CharacterSelectedClassModifierCrossRef(
    @ColumnInfo(index = true) val characterId: Long,
    @ColumnInfo(index = true) val modifierId: Long
)

