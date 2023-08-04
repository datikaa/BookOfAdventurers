package com.datikaa.boa.launcher.domain

import com.datikaa.boa.core.data.CharacterRepository

class FlowCharacterNamesUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke() = characterRepository.flowListOfCharacters()
}
