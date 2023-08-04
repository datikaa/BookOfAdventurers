package com.datikaa.boa.feature.editor.domain

import com.datikaa.boa.core.domain.Modifier
import com.datikaa.boa.core.data.CharacterRepository
import com.datikaa.boa.core.data.ModifierRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class FlowSelectedModifiersForCharacterUseCase(
    private val characterRepository: CharacterRepository,
    private val modifierRepository: ModifierRepository,
) {

    operator fun invoke(characterId: Int): Flow<Map<Modifier, Boolean>> = combine(
        characterRepository.flowCharacter(characterId),
        modifierRepository.flowAllModifiers(),
    ) { character, modifiers ->
        modifiers.associateWith { modifier -> character.modifiers.any { it.id == modifier.id } }
    }
}
