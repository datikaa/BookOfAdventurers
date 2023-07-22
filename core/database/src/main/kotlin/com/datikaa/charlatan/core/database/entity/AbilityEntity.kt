package com.datikaa.charlatan.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = CharacterEntity::class,
        parentColumns = ["id"],
        childColumns = ["characterId"],
    )]
)
data class AbilityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val characterId: Int,
    val type: Type,
    val value: Int,
) {
    enum class Type {
        Strength,
        Dexterity,
        Constitution,
        Intelligence,
        Wisdom,
        Charisma,
    }
}