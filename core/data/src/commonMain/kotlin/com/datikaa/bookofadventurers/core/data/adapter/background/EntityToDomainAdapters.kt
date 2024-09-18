package com.datikaa.bookofadventurers.core.data.adapter.background

import com.datikaa.bookofadventurers.core.data.adapter.modifier.mapToDomain
import com.datikaa.bookofadventurers.core.database.entity.BackgroundEntity
import com.datikaa.bookofadventurers.core.database.relation.BackgroundWithModifiers
import com.datikaa.bookofadventurers.core.domain.Background

internal fun BackgroundWithModifiers.toDomain() = Background(
    id = backgroundEntity.id,
    name = backgroundEntity.name,
    featureTitle = backgroundEntity.featureTitle,
    featureDescription = backgroundEntity.featureDescription,
    suggestedCharacteristics = backgroundEntity.suggestedCharacteristics,
    skillProficiencies = skillProficiencyModifierEntities.mapToDomain(),
)

internal fun BackgroundEntity.toDomain() = Background(
    id = id,
    name = name,
    featureTitle = featureTitle,
    featureDescription = featureDescription,
    suggestedCharacteristics = suggestedCharacteristics,
    skillProficiencies = emptyList(),
)
