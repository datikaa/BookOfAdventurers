package com.datikaa.charlatan.launcher.domain

import com.datikaa.core.data.CharacterRepository

class ClearEverythingUseCase(
    private val repository: CharacterRepository,
) {

    suspend operator fun invoke() {
        repository.clearAll()
    }
}
