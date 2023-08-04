package com.datikaa.boa.feature.editor.domain

import com.datikaa.boa.core.data.CharacterRepository
import com.datikaa.boa.core.domain.BoaCharacter
import kotlinx.coroutines.flow.Flow

class FlowCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke(characterId: Int): Flow<BoaCharacter> =
        characterRepository.flowCharacter(characterId)
}
