package com.datikaa.charlatan.feature.overview.domain

import com.datikaa.core.data.CharacterRepository

class ClearEverythingUseCase(
    private val repository: CharacterRepository,
) {

    suspend operator fun invoke() {
        repository.clearAll()
    }
}