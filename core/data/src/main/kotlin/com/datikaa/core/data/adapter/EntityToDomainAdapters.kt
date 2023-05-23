package com.datikaa.core.data.adapter

import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.entity.CharacterWithAttributes
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character

internal fun AttributeEntity.toDomain(): Ability = when(type) {
    AttributeEntity.Type.Strength -> Ability.Strength(value)
    AttributeEntity.Type.Dexterity -> Ability.Dexterity(value)
    AttributeEntity.Type.Constitution -> Ability.Constitution(value)
    AttributeEntity.Type.Intelligence -> Ability.Intelligence(value)
    AttributeEntity.Type.Wisdom -> Ability.Wisdom(value)
    AttributeEntity.Type.Charisma -> Ability.Charisma(value)
}

@JvmName("mapAttributeEntityToDomain")
internal fun List<AttributeEntity>.mapToDomain() = map { it.toDomain() }

internal fun CharacterWithAttributes.toDomain(): Character = Character(
    id = characterEntity.id,
    name = characterEntity.name,
    level = characterEntity.level,
    abilityList = attributes.mapToDomain(),
    modifiers = listOf(),
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