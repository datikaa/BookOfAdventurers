package com.bookofadventurers.feature.wizard.domain

import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass

class AddCharacterUseCase(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(
        name: String,
        backgroundId: Long,
        classId: Long,
        skillProficiencyIds: List<Int>,
    ) {
        val id = characterRepository.insertCharacter(
            createNew5eCharacter(name, backgroundId, classId)
        )
        characterRepository.linkCharacterWithSelectedModifiers(
            charId = id,
            modifierIds = skillProficiencyIds.map { it.toLong() },
        )
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

private fun createNew5eCharacter(name: String, backgroundId: Long, classId: Long) = BoaCharacter(
    id = 0,
    name = name,
    level = 1,
    characterBackground = Background(
        id = backgroundId,
        name = "",
        featureTitle = "",
        featureDescription = "",
        suggestedCharacteristics = "",
        skillProficiencies = listOf()

    ),
    characterClass = CharacterClass(classId, "", emptyList(), emptyList()),
    abilityList = defaultAbilityList,
    modifiers = listOf(),
)