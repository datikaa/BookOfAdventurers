package com.datikaa.charlatan.feature.attributes.domain

import com.datikaa.charlatan.core.database.CharAttribute
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IncreaseValueOfAttributeUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke(attribute: Attribute) {
        withContext(Dispatchers.IO) {
            database.characterDao().updateAttribute(
                CharAttribute(attribute.id, attribute.name, attribute.value + 1)
            )
        }
    }
}