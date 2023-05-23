package com.datikaa.charlatan.feature.overview.etc

import com.datikaa.charlatan.core.domain.Ability

val Ability.name: String
    get() = when (this) {
        is Ability.Charisma -> "Charisma"
        is Ability.Constitution -> "Constitution"
        is Ability.Dexterity -> "Dexterity"
        is Ability.Intelligence -> "Intelligence"
        is Ability.Strength -> "Strength"
        is Ability.Wisdom -> "Wisdom"
    }

val Ability.shortName: String
    get() = name.take(3)