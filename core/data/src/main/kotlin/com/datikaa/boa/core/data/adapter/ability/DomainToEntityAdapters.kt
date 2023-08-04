package com.datikaa.boa.core.data.adapter.ability

import com.datikaa.boa.core.database.entity.AbilityEntity
import com.datikaa.boa.core.database.partial.AbilityEntityPartialUpdate
import com.datikaa.boa.core.domain.Ability

internal fun List<Ability>.mapToEntity(characterId: Int): List<AbilityEntity> = map { ability ->
    ability.toEntity(0, characterId)
}

internal fun Ability.toEntity(id: Int, characterId: Int): AbilityEntity = AbilityEntity(
    id = id,
    characterId = characterId,
    type = toEntityEnum(),
    value = value,
)

internal fun Ability.toEntityEnum(): AbilityEntity.Type = when (this) {
    is Ability.Charisma -> AbilityEntity.Type.Charisma
    is Ability.Constitution -> AbilityEntity.Type.Constitution
    is Ability.Dexterity -> AbilityEntity.Type.Dexterity
    is Ability.Intelligence -> AbilityEntity.Type.Intelligence
    is Ability.Strength -> AbilityEntity.Type.Strength
    is Ability.Wisdom -> AbilityEntity.Type.Wisdom
}

internal fun Ability.toPartialUpdate(id: Int) = AbilityEntityPartialUpdate(
    id = id,
    value = value,
)
