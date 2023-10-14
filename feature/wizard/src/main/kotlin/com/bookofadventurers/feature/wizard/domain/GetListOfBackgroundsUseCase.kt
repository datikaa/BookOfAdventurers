package com.bookofadventurers.feature.wizard.domain

import com.datikaa.bookofadventurers.core.data.BackgroundRepository
import com.datikaa.bookofadventurers.core.domain.Background

class GetListOfBackgroundsUseCase(
    private val backgroundRepository: BackgroundRepository,
) {

    suspend operator fun invoke(): List<Background> =
        backgroundRepository.getAllBackgroundsWithModifiers()
}