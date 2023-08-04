package com.datikaa.bookofadventurers.feature.editor.domain

import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.domain.BoaCharacter

class ModifyLevelOfCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(character: BoaCharacter, type: Type) {
        characterRepository.updateCharacter(
            character.updateLevel(type)
        )
    }

    private fun BoaCharacter.updateLevel(type: Type) = when (type) {
        Type.Increase -> if (level < 20) copy(level = level.inc()) else this
        Type.Decrease -> if (level > 1) copy(level = level.dec()) else this
    }

    enum class Type {
        Increase,
        Decrease
    }
}
