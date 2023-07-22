package com.datikaa.charlatan.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = ModifierEntity::class,
        parentColumns = ["id"],
        childColumns = ["parentModifierId"],
    )]
)
data class ModifierEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val parentModifierId: Int?,
    val name: String,
    val description: String,
    val type: Type,
    val modifiableScoreType: ModifiableScoreType?,
    val modifierValue: Int?,
) {
    enum class Type {
        Holder,
        Score,
        Proficiency,
    }

    enum class ModifiableScoreType {
        BaseAbility_Strength,
        BaseAbility_Dexterity,
        BaseAbility_Constitution,
        BaseAbility_Intelligence,
        BaseAbility_Wisdom,
        BaseAbility_Charisma,
        SavingThrow_Strength,
        SavingThrow_Dexterity,
        SavingThrow_Constitution,
        SavingThrow_Intelligence,
        SavingThrow_Wisdom,
        SavingThrow_Charisma,
        Skill_Acrobatics,
        Skill_AnimalHandling,
        Skill_Arcana,
        Skill_Athletics,
        Skill_Deception,
        Skill_History,
        Skill_Insight,
        Skill_Intimidation,
        Skill_Investigation,
        Skill_Medicine,
        Skill_Nature,
        Skill_Perception,
        Skill_Performance,
        Skill_Persuasion,
        Skill_Religion,
        Skill_SleightOfHand,
        Skill_Stealth,
        Skill_Survival,
    }
}
