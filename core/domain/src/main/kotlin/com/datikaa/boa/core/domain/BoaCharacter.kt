package com.datikaa.boa.core.domain

data class BoaCharacter(
    val id: Int,
    val name: String,
    val level: Int,
    val abilityList: List<Ability>,
    val skills: List<Skill> = dnd5eSkillSet(),
    val savingThrow: List<SavingThrow> = dnd5eSavingThrowSet(),
    val modifiers: List<Modifier>,
)

private fun dnd5eSkillSet(): List<Skill> = listOf(
    Skill.Acrobatics,
    Skill.AnimalHandling,
    Skill.Arcana,
    Skill.Athletics,
    Skill.Deception,
    Skill.History,
    Skill.Insight,
    Skill.Intimidation,
    Skill.Investigation,
    Skill.Medicine,
    Skill.Nature,
    Skill.Perception,
    Skill.Performance,
    Skill.Persuasion,
    Skill.Religion,
    Skill.SleightOfHand,
    Skill.Stealth,
    Skill.Survival,
)

private fun dnd5eSavingThrowSet(): List<SavingThrow> = listOf(
    SavingThrow.Charisma,
    SavingThrow.Constitution,
    SavingThrow.Dexterity,
    SavingThrow.Intelligence,
    SavingThrow.Strength,
    SavingThrow.Wisdom,
)
