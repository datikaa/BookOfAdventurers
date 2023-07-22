package com.datikaa.charlatan.feature.editor.domain

import com.datikaa.core.data.ModifierRepository

class AddModifierToCharacterUseCase(
    private val modifierRepository: ModifierRepository,
) {

    suspend operator fun invoke(characterId: Int, modifierId: Int) {
        modifierRepository.associateModifierWithCharacter(modifierId.toLong(), characterId.toLong())
    }
}
