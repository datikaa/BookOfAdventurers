package com.datikaa.core.data.adapter.character

import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.domain.Character

internal fun Character.toEntity(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    level = level
)