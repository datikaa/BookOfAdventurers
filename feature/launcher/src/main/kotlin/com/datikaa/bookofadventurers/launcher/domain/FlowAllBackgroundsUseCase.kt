package com.datikaa.bookofadventurers.launcher.domain

import com.datikaa.bookofadventurers.core.data.BackgroundRepository

class FlowAllBackgroundsUseCase(
    private val backgroundRepository: BackgroundRepository,
) {

    operator fun invoke() = backgroundRepository.flowAllBackgrounds()
}