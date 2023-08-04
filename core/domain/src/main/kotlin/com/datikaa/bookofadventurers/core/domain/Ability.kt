package com.datikaa.bookofadventurers.core.domain

import kotlin.reflect.KClass

typealias AbilityType = KClass<out Ability>

// TODO: this needs to be refactored, tedious now
sealed interface Ability : ModifiableScore {

    val value: Int

    data class Strength(
        override val value: Int
    ) : Ability

    data class Dexterity(
        override val value: Int
    ) : Ability

    data class Constitution(
        override val value: Int
    ) : Ability

    data class Intelligence(
        override val value: Int
    ) : Ability

    data class Wisdom(
        override val value: Int
    ) : Ability

    data class Charisma(
        override val value: Int
    ) : Ability
}

fun Ability.copy(newValue: Int): Ability = when (this) {
    is Ability.Charisma -> copy(newValue)
    is Ability.Constitution -> copy(newValue)
    is Ability.Dexterity -> copy(newValue)
    is Ability.Intelligence -> copy(newValue)
    is Ability.Strength -> copy(newValue)
    is Ability.Wisdom -> copy(newValue)
}
