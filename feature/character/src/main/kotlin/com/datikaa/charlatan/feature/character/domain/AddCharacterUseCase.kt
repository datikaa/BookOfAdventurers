package com.datikaa.charlatan.feature.character.domain

import com.datikaa.charlatan.core.database.Character
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCharacterUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke(name: String) {
        withContext(Dispatchers.IO) {
            database.characterDao().insert(
                Character(0, name)
            )
        }
    }
}