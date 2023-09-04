package com.datikaa.bookofadventurers.core.data.adapter.classes

import com.datikaa.bookofadventurers.core.data.adapter.modifier.mapToDomain
import com.datikaa.bookofadventurers.core.database.entity.ClassEntity
import com.datikaa.bookofadventurers.core.database.entity.ClassWithModifiers
import com.datikaa.bookofadventurers.core.domain.BoaClass

@JvmName("mapClassWithModifiersToDomain")
internal fun List<ClassWithModifiers>.mapToDomain() = map { it.toDomain() }
internal fun ClassWithModifiers.toDomain() = BoaClass(
    id = classEntity.id,
    name = classEntity.name,
    selectableSkillCount = classEntity.selectableSkillCount,
    savingThrowProficiencies = savingThrowProficiencyModifierEntities.mapToDomain(),
    selectableSkillProficiencies = skillProficiencyModifierEntities.mapToDomain(),
)

@JvmName("mapClassEntitiesToDomain")
internal fun List<ClassEntity>.mapToDomain() = map { it.toDomain() }

internal fun ClassEntity.toDomain() = BoaClass(
    id = id,
    name = name,
    selectableSkillCount = selectableSkillCount,
    savingThrowProficiencies = emptyList(),
    selectableSkillProficiencies = emptyList(),
)
