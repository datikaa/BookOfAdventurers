package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.domain.Modifier
import kotlinx.coroutines.flow.Flow

interface ModifierRepository {
    suspend fun insertModifier(modifier: Modifier): Long


    @Deprecated("Use with Modifier")
    suspend fun associateModifierWithCharacter(modifierId: Long, characterId: Long)

    //TODO: move this to CharacterRepository? :thinking:
    suspend fun associateModifierWithCharacter(modifier: Modifier, characterId: Long)

    fun flowAllModifiers(): Flow<List<Modifier>>

    suspend fun getModifier(id: Int): Modifier

    suspend fun getCharacterModifierCrossRef(
        modifierId: Long,
        characterId: Long
    ):  Pair<Long, Long>?

    suspend fun removeCharacterModifierCrossRef(
        modifierId: Long,
        characterId: Long
    )
}
