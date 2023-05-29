package com.datikaa.charlatan.feature.editor.domain

import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.core.data.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(name: String) {
        withContext(Dispatchers.IO) {
            characterRepository.insertCharacter(
                createNew5eCharacter(name)
            )
        }
    }
}

private fun createNew5eCharacter(name: String) = Character(
    id = 0,
    name = name,
    level = 1,
    abilityList = listOf(
        Ability.Strength(0),
        Ability.Dexterity(0),
        Ability.Constitution(0),
        Ability.Intelligence(0),
        Ability.Wisdom(0),
        Ability.Charisma(0),
    ),
    modifiers = listOf()
)