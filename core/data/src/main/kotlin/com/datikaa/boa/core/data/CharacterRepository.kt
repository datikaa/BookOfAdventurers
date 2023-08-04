package com.datikaa.boa.core.data

import com.datikaa.boa.core.domain.BoaCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun flowListOfCharacters(): Flow<List<BoaCharacter>>
    fun flowCharacter(id: Int): Flow<BoaCharacter>
    suspend fun updateCharacter(character: BoaCharacter)
    suspend fun insertCharacter(character: BoaCharacter): Long
    suspend fun clearAll()
}
