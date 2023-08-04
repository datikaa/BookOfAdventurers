package com.datikaa.boa.launcher.domain

import com.datikaa.boa.core.data.CharacterRepository

class ClearEverythingUseCase(
    private val repository: CharacterRepository,
) {

    suspend operator fun invoke() {
        repository.clearAll()
    }
}
