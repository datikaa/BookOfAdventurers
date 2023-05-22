package com.datikaa.charlatan.core.domain
sealed class Skill(val ability: AbilityType) : ModifiableScore, PossiblyProficient {
    object Acrobatics : Skill(ability = Ability.Dexterity::class)
    object AnimalHandling : Skill(ability = Ability.Wisdom::class)
    object Arcana : Skill(ability = Ability.Intelligence::class)
    object Athletics : Skill(ability = Ability.Strength::class)
    object Deception : Skill(ability = Ability.Charisma::class)
    object History : Skill(ability = Ability.Intelligence::class)
    object Insight : Skill(ability = Ability.Wisdom::class)
    object Intimidation : Skill(ability = Ability.Charisma::class)
    object Investigation : Skill(ability = Ability.Intelligence::class)
    object Medicine : Skill(ability = Ability.Wisdom::class)
    object Nature : Skill(ability = Ability.Intelligence::class)
    object Perception : Skill(ability = Ability.Wisdom::class)
    object Performance : Skill(ability = Ability.Charisma::class)
    object Persuasion : Skill(ability = Ability.Charisma::class)
    object Religion : Skill(ability = Ability.Intelligence::class)
    object SleightOfHand : Skill(ability = Ability.Dexterity::class)
    object Stealth : Skill(ability = Ability.Dexterity::class)
    object Survival : Skill(ability = Ability.Wisdom::class)
}