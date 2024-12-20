package com.datikaa.bookofadventurers.core.data.adapter.character

import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.domain.BoaCharacter

internal fun BoaCharacter.toEntity(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    level = level
)
