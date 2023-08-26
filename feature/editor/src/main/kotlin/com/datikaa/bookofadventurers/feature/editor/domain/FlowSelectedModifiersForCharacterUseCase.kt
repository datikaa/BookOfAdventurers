package com.datikaa.bookofadventurers.feature.editor.domain

import android.util.Log
import com.datikaa.bookofadventurers.core.domain.Modifier
import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.data.ModifierRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach

class FlowSelectedModifiersForCharacterUseCase(
    private val characterRepository: CharacterRepository,
    private val modifierRepository: ModifierRepository,
) {

    operator fun invoke(characterId: Int): Flow<Map<Modifier, Boolean>> = combine(
        characterRepository.flowCharacter(characterId).onEach {
            Log.d("test", it.toString())
        },
        modifierRepository.flowAllModifiers(),
    ) { character, modifiers ->
        modifiers.associateWith { modifier -> character.modifiers.any { it.id == modifier.id } }
    }
}
