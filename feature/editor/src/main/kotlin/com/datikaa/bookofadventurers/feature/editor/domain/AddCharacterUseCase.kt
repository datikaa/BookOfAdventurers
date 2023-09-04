package com.datikaa.bookofadventurers.feature.editor.domain

import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.BoaClass

private fun createNew5eCharacter(name: String, classId: Int) = BoaCharacter(
    id = 0,
    name = name,
    level = 1,
    boaClass = BoaClass(
        id = classId.toLong(),
        name = "",
        selectableSkillCount = 0,
        savingThrowProficiencies = emptyList(),
        selectableSkillProficiencies = emptyList(),
    ),
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
