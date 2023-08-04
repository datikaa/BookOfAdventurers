package com.datikaa.bookofadventurers.core.domain

sealed class Skill(override val baseAbility: AbilityType) : ModifiableScore, CalculatedScore, PossiblyProficient {
    object Acrobatics : Skill(baseAbility = Ability.Dexterity::class)
    object AnimalHandling : Skill(baseAbility = Ability.Wisdom::class)
    object Arcana : Skill(baseAbility = Ability.Intelligence::class)
    object Athletics : Skill(baseAbility = Ability.Strength::class)
    object Deception : Skill(baseAbility = Ability.Charisma::class)
    object History : Skill(baseAbility = Ability.Intelligence::class)
    object Insight : Skill(baseAbility = Ability.Wisdom::class)
    object Intimidation : Skill(baseAbility = Ability.Charisma::class)
    object Investigation : Skill(baseAbility = Ability.Intelligence::class)
    object Medicine : Skill(baseAbility = Ability.Wisdom::class)
    object Nature : Skill(baseAbility = Ability.Intelligence::class)
    object Perception : Skill(baseAbility = Ability.Wisdom::class)
    object Performance : Skill(baseAbility = Ability.Charisma::class)
    object Persuasion : Skill(baseAbility = Ability.Charisma::class)
    object Religion : Skill(baseAbility = Ability.Intelligence::class)
    object SleightOfHand : Skill(baseAbility = Ability.Dexterity::class)
    object Stealth : Skill(baseAbility = Ability.Dexterity::class)
    object Survival : Skill(baseAbility = Ability.Wisdom::class)
}
