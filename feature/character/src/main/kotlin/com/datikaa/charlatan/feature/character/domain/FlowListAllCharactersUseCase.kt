package com.datikaa.charlatan.feature.character.domain

import com.datikaa.core.data.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FlowListAllCharactersUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke(): Flow<List<CmmCharacter>> =
        characterRepository.flowListOfCharacters().map { characters ->
            characters.map {
                CmmCharacter(
                    id = it.id,
                    name = it.name,
                )
            }
        }
}