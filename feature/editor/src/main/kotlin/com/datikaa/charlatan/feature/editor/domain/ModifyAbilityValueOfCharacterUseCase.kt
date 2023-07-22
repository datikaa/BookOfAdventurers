package com.datikaa.charlatan.feature.editor.domain

import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.core.domain.copy
import com.datikaa.core.data.AbilityRepository

class ModifyAbilityValueOfCharacterUseCase(
    private val abilityRepository: AbilityRepository,
) {

    suspend operator fun invoke(character: Character, ability: Ability, type: Type) {
        abilityRepository.updateAbility(
            characterId = character.id,
            ability = ability.update(type),
        )
    }

    private fun Ability.update(type: Type) = when (type) {
        Type.Increase -> if (value < 20) copy(value.inc()) else this
        Type.Decrease -> if (value > 3) copy(value.dec()) else this
    }

    enum class Type {
        Increase,
        Decrease
    }
}