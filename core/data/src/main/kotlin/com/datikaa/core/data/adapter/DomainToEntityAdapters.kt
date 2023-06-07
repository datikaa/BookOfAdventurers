package com.datikaa.core.data.adapter

import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.partial.AttributeEntityPartialUpdate
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character

internal fun Ability.toEntity(id: Int, characterId: Int): AttributeEntity = AttributeEntity(
    id = id,
    characterId = characterId,
    type = toEntityEnum(),
    value = 0,
)

internal fun Ability.toEntityEnum(): AttributeEntity.Type = when (this) {
    is Ability.Charisma -> AttributeEntity.Type.Charisma
    is Ability.Constitution -> AttributeEntity.Type.Constitution
    is Ability.Dexterity -> AttributeEntity.Type.Dexterity
    is Ability.Intelligence -> AttributeEntity.Type.Intelligence
    is Ability.Strength -> AttributeEntity.Type.Strength
    is Ability.Wisdom -> AttributeEntity.Type.Wisdom
}

internal fun List<Ability>.mapToEntity(characterId: Int): List<AttributeEntity> = map { ability ->
    ability.toEntity(0, characterId)
}

internal fun Character.toEntity(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    level = level
)

internal fun Ability.toPartialUpdate(id: Int) = AttributeEntityPartialUpdate(
    id = id,
    value = value,
)