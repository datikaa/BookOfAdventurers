package com.bookofadventurers.feature.background.domain

import com.datikaa.bookofadventurers.core.data.ModifierRepository

class FlowAllModifiersUseCase(
    private val modifierRepository: ModifierRepository,
) {

    operator fun invoke() = modifierRepository.flowAllModifiers()
}
