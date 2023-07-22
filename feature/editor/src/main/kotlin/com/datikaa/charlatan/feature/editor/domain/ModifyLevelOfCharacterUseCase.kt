package com.datikaa.charlatan.feature.editor.domain

import com.datikaa.charlatan.core.domain.Character
import com.datikaa.core.data.CharacterRepository

class ModifyLevelOfCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(character: Character, type: Type) {
        characterRepository.updateCharacter(
            character.updateLevel(type)
        )
    }

    private fun Character.updateLevel(type: Type) = when (type) {
        Type.Increase -> if (level < 20) copy(level = level.inc()) else this
        Type.Decrease -> if (level > 1) copy(level = level.dec()) else this
    }

    enum class Type {
        Increase,
        Decrease
    }
}