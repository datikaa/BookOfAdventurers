package com.datikaa.charlatan.feature.overview.domain

import com.datikaa.charlatan.core.database.Character
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddOrEditCharacterUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke(name: String) {
        withContext(Dispatchers.IO) {
            database.characterDao().insert(
                Character(1, name)
            )
        }
    }
}