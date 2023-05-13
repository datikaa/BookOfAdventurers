package com.datikaa.charlatan.feature.attributes.domain

import com.datikaa.charlatan.core.database.CharAttribute
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DecreaseValueOfAttributeUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke(attribute: Attribute) {
        withContext(Dispatchers.IO) {
            database.attributesDao().updateAttribute(
                CharAttribute(attribute.id, attribute.name, attribute.value - 1)
            )
        }
    }
}