package com.datikaa.bookofadventurers.feature.editor.domain

import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import kotlinx.coroutines.flow.Flow

class FlowListAllCharactersUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke(): Flow<List<BoaCharacter>> = characterRepository.flowListOfCharacters()
}
