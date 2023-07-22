package com.datikaa.core.data.adapter.character

import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.entity.CharacterWithAbilitiesAndModifiers
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.core.data.adapter.ability.mapToDomain
import com.datikaa.core.data.adapter.modifier.mapToDomain

internal fun CharacterWithAbilitiesAndModifiers.toDomain(): Character = Character(
    id = characterEntity.id,
    name = characterEntity.name,
    level = characterEntity.level,
    abilityList = abilityEntities.mapToDomain(),
    modifiers = modifierEntities.mapToDomain(),
)

@JvmName("mapCharacterEntityToDomain")
internal fun List<CharacterEntity>.mapToDomain() = map { it.toDomain() }
internal fun CharacterEntity.toDomain(): Character = Character(
    id = id,
    name = name,
    level = level,
    abilityList = emptyList(),
    modifiers = emptyList(),
)
