package com.datikaa.bookofadventurers.core.store.models

import kotlinx.serialization.Serializable

@Serializable
data class ModifierEntity(
    val id: Int,
    val parentModifierId: Int?,
    val name: String,
    val description: String,
    val type: Type,
    val modifiableScoreType: ModifiableScoreType?,
    val modifierValue: Int?,
) {
    @Serializable
    enum class Type {
        Holder,
        Score,
        Proficiency,
    }

    @Serializable
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
