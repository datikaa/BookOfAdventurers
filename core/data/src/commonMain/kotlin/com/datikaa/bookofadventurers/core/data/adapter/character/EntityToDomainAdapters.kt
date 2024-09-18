package com.datikaa.bookofadventurers.core.data.adapter.character

import com.datikaa.bookofadventurers.core.data.adapter.ability.mapListToDomain
import com.datikaa.bookofadventurers.core.data.adapter.background.toDomain
import com.datikaa.bookofadventurers.core.data.adapter.modifier.mapToDomain
import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.database.relation.CharacterWithAbilitiesAndModifiers
import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass

internal fun CharacterWithAbilitiesAndModifiers.toDomain(): BoaCharacter = BoaCharacter(
    id = characterEntity.id,
    name = characterEntity.name,
    level = characterEntity.level,
    characterBackground = mapToBackground(),
    characterClass = mapToCharacterClass(),
    abilityList = abilityEntities.mapListToDomain(),
    modifiers = modifierEntities.mapToDomain(),
)

private fun CharacterWithAbilitiesAndModifiers.mapToCharacterClass(): CharacterClass {
    val firstCharacterClass = classWithModifiersEntities.first()
    return CharacterClass(
        id = firstCharacterClass.classEntity.id,
        name = firstCharacterClass.classEntity.name,
        savingThrowProficiencies = firstCharacterClass.savingThrowProficiencyModifierEntities.mapToDomain(),
        skillProficiencies = selectedClassSkillModifierEntities.mapToDomain()
    )
}

private fun CharacterWithAbilitiesAndModifiers.mapToBackground(): Background {
    return backgroundWithModifiersEntities.first().toDomain()
}

internal fun CharacterEntity.toDomain(): BoaCharacter = BoaCharacter(
    id = id,
    name = name,
    level = level,
    characterBackground = Background(
        id = -1,
        name = "",
        featureTitle = "",
        featureDescription = "",
        suggestedCharacteristics = "",
        skillProficiencies = listOf(),
    ),
    characterClass = CharacterClass(
        id = -1,
        name = "",
        savingThrowProficiencies = emptyList(),
        skillProficiencies = emptyList(),
    ),
    abilityList = emptyList(),
    modifiers = emptyList(),
)
