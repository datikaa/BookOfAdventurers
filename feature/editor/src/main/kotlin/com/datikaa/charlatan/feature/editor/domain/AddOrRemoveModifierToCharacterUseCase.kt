package com.datikaa.charlatan.feature.editor.domain

import com.datikaa.core.data.ModifierRepository

class AddOrRemoveModifierToCharacterUseCase(
    private val modifierRepository: ModifierRepository,
) {

    suspend operator fun invoke(characterId: Int, modifierId: Int) {
        val crossRef = modifierRepository.getCharacterModifierCrossRef(modifierId.toLong(), characterId.toLong())
        if (crossRef == null) {
            modifierRepository.associateModifierWithCharacter(modifierId.toLong(), characterId.toLong())
        } else {
            modifierRepository.removeCharacterModifierCrossRef(modifierId.toLong(), characterId.toLong())
        }
    }
}