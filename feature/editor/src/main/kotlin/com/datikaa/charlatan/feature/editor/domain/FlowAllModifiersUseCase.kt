package com.datikaa.charlatan.feature.editor.domain

import com.datikaa.core.data.ModifierRepository

class FlowAllModifiersUseCase(
    private val modifierRepository: ModifierRepository,
) {

    operator fun invoke() = modifierRepository.flowAllModifiers()
}
