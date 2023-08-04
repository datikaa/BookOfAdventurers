package com.datikaa.boa.core.domain

sealed interface ModifiableScore

sealed interface CalculatedScore : ModifiableScore {
    val baseAbility: AbilityType
}
