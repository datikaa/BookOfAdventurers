package com.datikaa.charlatan.feature.overview.domain

import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.core.data.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FlowListOfAttributesUseCase(
    private val characterRepository: CharacterRepository,
) {

    operator fun invoke(characterId: Int): Flow<List<Ability>> =
        characterRepository.flowCharacter(characterId).map { it.abilityList }
}