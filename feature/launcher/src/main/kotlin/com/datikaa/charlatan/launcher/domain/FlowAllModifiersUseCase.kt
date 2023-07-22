package com.datikaa.charlatan.launcher.domain

import com.datikaa.core.data.ModifierRepository

class FlowAllModifiersUseCase(
    private val modifierRepository: ModifierRepository,
) {

    operator fun invoke() = modifierRepository.flowAllModifiers()
}
