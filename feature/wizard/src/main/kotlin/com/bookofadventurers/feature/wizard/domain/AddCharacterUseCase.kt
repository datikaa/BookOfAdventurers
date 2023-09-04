package com.bookofadventurers.feature.wizard.domain

import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.BoaClass

class AddCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(name: String, classId: Long, skillProficiencyIds: List<Int>) {
        val id = characterRepository.insertCharacter(
            createNew5eCharacter(name, classId)
        )
        characterRepository.linkCharacterWithSelectedModifiers(id, skillProficiencyIds.map { it.toLong() })
    }
}

private val defaultAbilityList: List<Ability>
    get() = listOf(
        Ability.Strength(10),
        Ability.Dexterity(10),
        Ability.Constitution(10),
        Ability.Intelligence(10),
        Ability.Wisdom(10),
        Ability.Charisma(10),
    )

private fun createNew5eCharacter(name: String, classId: Long) = BoaCharacter(
    id = 0,
    name = name,
    level = 1,
    boaClass = BoaClass(classId, "", 0, emptyList(), emptyList()),
    abilityList = defaultAbilityList,
    modifiers = listOf()
)