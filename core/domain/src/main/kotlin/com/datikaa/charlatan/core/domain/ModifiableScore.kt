package com.datikaa.charlatan.core.domain

sealed interface ModifiableScore

sealed interface CalculatedScore : ModifiableScore {
    val baseAbility: AbilityType
}