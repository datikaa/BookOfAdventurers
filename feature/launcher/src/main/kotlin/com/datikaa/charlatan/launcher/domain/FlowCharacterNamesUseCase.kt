package com.datikaa.charlatan.launcher.domain

import com.datikaa.core.data.CharacterRepository

class FlowCharacterNamesUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke() = characterRepository.flowListOfCharacters()
}
