package com.datikaa.bookofadventurers.feature.editor.domain

import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.domain.BoaCharacter

class AddCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(name: String) {
        characterRepository.insertCharacter(
            createNew5eCharacter(name)
        )
    }
}

private fun createNew5eCharacter(name: String) = BoaCharacter(
    id = 0,
    name = name,
    level = 1,
    abilityList = listOf(
        Ability.Strength(10),
        Ability.Dexterity(10),
        Ability.Constitution(10),
        Ability.Intelligence(10),
        Ability.Wisdom(10),
        Ability.Charisma(10),
    ),
    modifiers = listOf()
)
