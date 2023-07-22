package com.datikaa.core.data

import com.datikaa.charlatan.core.domain.Modifier

interface ModifierRepository {
    suspend fun insertModifier(modifier: Modifier): Long

    suspend fun associateModifierWithCharacter(modifierId: Long, characterId: Long)
}