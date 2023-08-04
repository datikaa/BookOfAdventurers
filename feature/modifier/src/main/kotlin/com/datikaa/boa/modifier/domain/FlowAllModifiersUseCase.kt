package com.datikaa.boa.modifier.domain

import com.datikaa.boa.core.data.ModifierRepository

class FlowAllModifiersUseCase(
    private val modifierRepository: ModifierRepository,
) {

    operator fun invoke() = modifierRepository.flowAllModifiers()
}
