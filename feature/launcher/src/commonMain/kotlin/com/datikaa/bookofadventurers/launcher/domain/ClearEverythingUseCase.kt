package com.datikaa.bookofadventurers.launcher.domain

import com.datikaa.bookofadventurers.core.data.CharacterRepository

class ClearEverythingUseCase(
    private val repository: CharacterRepository,
) {

    suspend operator fun invoke() {
        repository.clearAll()
    }
}
