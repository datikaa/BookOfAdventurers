package com.datikaa.bookofadventurers.feature.editor.domain

import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.data.ModifierRepository
import com.datikaa.bookofadventurers.core.domain.Modifier
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
        val classModifiers = with(character.characterClass) {
            skillProficiencies + savingThrowProficiencies
        }
        modifiers
            .filter { modifier -> classModifiers.map { it.id }.contains(modifier.id) }
            .associateWith { modifier -> character.modifiers.any { it.id == modifier.id } }
    }
}
