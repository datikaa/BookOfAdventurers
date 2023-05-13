package com.datikaa.charlatan.feature.character.domain

import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCharacterUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke(name: String) {
        withContext(Dispatchers.IO) {
            database.characterDao().insert(
                CharacterEntity(
                    id = 0,
                    name = name,
                    level = 1,
                )
            )
        }
    }
}