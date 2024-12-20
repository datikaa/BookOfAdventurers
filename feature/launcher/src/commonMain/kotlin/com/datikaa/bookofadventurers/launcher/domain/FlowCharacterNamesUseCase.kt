package com.datikaa.bookofadventurers.launcher.domain

import com.datikaa.bookofadventurers.core.data.CharacterRepository

class FlowCharacterNamesUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke() = characterRepository.flowListOfCharacters()
}
