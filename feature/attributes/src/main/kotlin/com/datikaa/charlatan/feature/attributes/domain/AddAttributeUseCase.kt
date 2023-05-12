package com.datikaa.charlatan.feature.attributes.domain

import com.datikaa.charlatan.core.database.CharAttribute
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddAttributeUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke(text: String, value: Int) {
        withContext(Dispatchers.IO) {
            database.characterDao().insertAttribute(
                CharAttribute(0, text, value)
            )
        }
    }
}