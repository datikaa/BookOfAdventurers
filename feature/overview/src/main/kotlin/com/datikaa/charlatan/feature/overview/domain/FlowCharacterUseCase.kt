package com.datikaa.charlatan.feature.overview.domain

import com.datikaa.charlatan.core.domain.Character
import com.datikaa.core.data.CharacterRepository
import kotlinx.coroutines.flow.Flow

class FlowCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke(characterId: Int): Flow<Character> =
        characterRepository.flowCharacter(characterId)
}