package com.datikaa.bookofadventurers.feature.editor.domain

import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import kotlinx.coroutines.flow.Flow

class FlowCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke(characterId: Int): Flow<BoaCharacter> =
        characterRepository.flowCharacter(characterId)
}
