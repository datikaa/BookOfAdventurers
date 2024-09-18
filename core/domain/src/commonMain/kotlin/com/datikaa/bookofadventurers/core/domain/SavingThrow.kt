package com.datikaa.bookofadventurers.core.domain

sealed class SavingThrow(override val baseAbility: AbilityType) : ModifiableScore, CalculatedScore, PossiblyProficient {
    object Strength : SavingThrow(baseAbility = Ability.Strength::class)
    object Dexterity : SavingThrow(baseAbility = Ability.Dexterity::class)
    object Constitution : SavingThrow(baseAbility = Ability.Constitution::class)
    object Intelligence : SavingThrow(baseAbility = Ability.Intelligence::class)
    object Wisdom : SavingThrow(baseAbility = Ability.Wisdom::class)
    object Charisma : SavingThrow(baseAbility = Ability.Charisma::class)
}
