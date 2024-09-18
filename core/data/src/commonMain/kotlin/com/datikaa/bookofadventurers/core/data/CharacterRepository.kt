package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun flowListOfCharacters(): Flow<List<BoaCharacter>>
    fun flowCharacter(id: Int): Flow<BoaCharacter>
    suspend fun updateCharacter(character: BoaCharacter)
    suspend fun insertCharacter(character: BoaCharacter): Long
    suspend fun clearAll()
    suspend fun linkCharacterWithSelectedModifiers(charId: Long, modifierIds: List<Long>)
}
