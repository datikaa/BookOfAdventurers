package com.datikaa.core.data

import com.datikaa.charlatan.core.domain.Modifier
import kotlinx.coroutines.flow.Flow

interface ModifierRepository {
    suspend fun insertModifier(modifier: Modifier): Long

    suspend fun associateModifierWithCharacter(modifierId: Long, characterId: Long)

    suspend fun deleteAllModifiers()

    fun flowAllModifiers(): Flow<List<Modifier>>
}
