package com.datikaa.bookofadventurers.core.data.adapter.ability

import com.datikaa.bookofadventurers.core.database.entity.AbilityEntity
import com.datikaa.bookofadventurers.core.domain.Ability

internal fun AbilityEntity.toDomain(): Ability = when (type) {
    AbilityEntity.Type.Strength -> Ability.Strength(value)
    AbilityEntity.Type.Dexterity -> Ability.Dexterity(value)
    AbilityEntity.Type.Constitution -> Ability.Constitution(value)
    AbilityEntity.Type.Intelligence -> Ability.Intelligence(value)
    AbilityEntity.Type.Wisdom -> Ability.Wisdom(value)
    AbilityEntity.Type.Charisma -> Ability.Charisma(value)
}

@JvmName("mapAbilityEntityToDomain")
internal fun List<AbilityEntity>.mapToDomain() = map { it.toDomain() }
