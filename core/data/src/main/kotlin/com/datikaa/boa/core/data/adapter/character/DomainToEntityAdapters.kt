package com.datikaa.boa.core.data.adapter.character

import com.datikaa.boa.core.database.entity.CharacterEntity
import com.datikaa.boa.core.domain.BoaCharacter

internal fun BoaCharacter.toEntity(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    level = level
)
