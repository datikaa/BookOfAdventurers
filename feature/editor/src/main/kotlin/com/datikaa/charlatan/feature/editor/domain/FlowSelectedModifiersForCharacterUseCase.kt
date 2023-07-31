package com.datikaa.charlatan.feature.editor.domain

import com.datikaa.charlatan.core.domain.Modifier
import com.datikaa.core.data.CharacterRepository
import com.datikaa.core.data.ModifierRepository
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
