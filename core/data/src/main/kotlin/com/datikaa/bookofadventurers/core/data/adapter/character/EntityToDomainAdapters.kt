package com.datikaa.bookofadventurers.core.data.adapter.character

import com.datikaa.bookofadventurers.core.data.adapter.ability.mapToDomain
import com.datikaa.bookofadventurers.core.data.adapter.modifier.mapToDomain
import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.database.relation.CharacterWithAbilitiesAndModifiers
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass

internal fun CharacterWithAbilitiesAndModifiers.toDomain(): BoaCharacter = BoaCharacter(
    id = characterEntity.id,
    name = characterEntity.name,
    level = characterEntity.level,
    characterClass = mapToCharacterClass(),
    abilityList = abilityEntities.mapToDomain(),
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

@JvmName("mapCharacterEntityToDomain")
internal fun List<CharacterEntity>.mapToDomain() = map { it.toDomain() }

internal fun CharacterEntity.toDomain(): BoaCharacter = BoaCharacter(
    id = id,
    name = name,
    level = level,
    characterClass = CharacterClass(-1, "", emptyList(), emptyList()),
    abilityList = emptyList(),
    modifiers = emptyList(),
)


