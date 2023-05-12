package com.datikaa.charlatan.feature.overview.domain

import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClearEverythingUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            database.attributesDao().deleteAttributes()
            database.characterDao().deleteCharacter()
        }
    }
}