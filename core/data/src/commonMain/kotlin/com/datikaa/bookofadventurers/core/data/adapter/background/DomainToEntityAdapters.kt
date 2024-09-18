package com.datikaa.bookofadventurers.core.data.adapter.background

import com.datikaa.bookofadventurers.core.database.entity.BackgroundEntity
import com.datikaa.bookofadventurers.core.domain.Background

internal fun List<Background>.mapToEntity(): List<BackgroundEntity> = map { ability ->
    ability.toEntity()
}

internal fun Background.toEntity(): BackgroundEntity = BackgroundEntity(
    id = id,
    name = name,
    featureTitle = featureTitle,
    featureDescription = featureDescription,
    suggestedCharacteristics = suggestedCharacteristics,
)
