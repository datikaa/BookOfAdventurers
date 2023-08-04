package com.datikaa.boa.feature.editor.domain

import com.datikaa.boa.core.data.CharacterRepository
import com.datikaa.boa.core.domain.BoaCharacter
import kotlinx.coroutines.flow.Flow

class FlowListAllCharactersUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke(): Flow<List<BoaCharacter>> = characterRepository.flowListOfCharacters()
}
