package com.bookofadventurers.feature.background.domain

import com.datikaa.bookofadventurers.core.data.BackgroundRepository
import com.datikaa.bookofadventurers.core.domain.Background

class AddNewBackgroundUseCase(
    private val backgroundRepository: BackgroundRepository,
) {

    suspend operator fun invoke(
        name: String,
        featureTitle: String,
        featureDescription: String,
        suggestedCharacteristics: String,
        modifierIds: List<Long>,
    ) {
        val newBackground = Background(
            id = 0,
            name = name,
            featureTitle = featureTitle,
            featureDescription = featureDescription,
            suggestedCharacteristics = suggestedCharacteristics,
            skillProficiencies = listOf()
        )
        val newBackgroundId = backgroundRepository.insertBackground(newBackground)
        backgroundRepository.associateBackgroundWithModifier(newBackgroundId, modifierIds)
    }
}