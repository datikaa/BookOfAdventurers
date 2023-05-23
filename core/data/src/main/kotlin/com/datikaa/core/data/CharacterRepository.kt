package com.datikaa.core.data

import com.datikaa.charlatan.core.domain.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun flowListOfCharacters(): Flow<List<Character>>
    fun flowCharacter(id: Int): Flow<Character>

    suspend fun insertCharacter(character: Character)

    suspend fun clearAll()
}