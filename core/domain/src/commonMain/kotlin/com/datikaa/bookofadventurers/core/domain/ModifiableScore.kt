package com.datikaa.bookofadventurers.core.domain

sealed interface ModifiableScore

sealed interface CalculatedScore : ModifiableScore {
    val baseAbility: AbilityType
}
