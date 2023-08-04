package com.datikaa.bookofadventurers.core.data.adapter.character

import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.database.entity.CharacterWithAbilitiesAndModifiers
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.data.adapter.ability.mapToDomain
import com.datikaa.bookofadventurers.core.data.adapter.modifier.mapToDomain

internal fun CharacterWithAbilitiesAndModifiers.toDomain(): BoaCharacter = BoaCharacter(
    id = characterEntity.id,
    name = characterEntity.name,
    level = characterEntity.level,
    abilityList = abilityEntities.mapToDomain(),
    modifiers = modifierEntities.mapToDomain(),
)

@JvmName("mapCharacterEntityToDomain")
internal fun List<CharacterEntity>.mapToDomain() = map { it.toDomain() }
internal fun CharacterEntity.toDomain(): BoaCharacter = BoaCharacter(
    id = id,
    name = name,
    level = level,
    abilityList = emptyList(),
    modifiers = emptyList(),
)
