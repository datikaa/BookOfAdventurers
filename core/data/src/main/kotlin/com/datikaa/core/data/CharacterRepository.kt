package com.datikaa.core.data

import com.datikaa.charlatan.core.domain.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun flowCharacter(id: Int): Flow<Character>

    suspend fun createOrUpdateCharacter(character: Character)

    suspend fun clearAll()
}