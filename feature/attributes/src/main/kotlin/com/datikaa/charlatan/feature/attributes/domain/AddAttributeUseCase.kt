package com.datikaa.charlatan.feature.attributes.domain

import com.datikaa.charlatan.core.database.CmmDatabase
import com.datikaa.charlatan.core.database.entity.AttributeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddAttributeUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke(text: String, charId: Int) {
        withContext(Dispatchers.IO) {
            database.attributesDao().insertAttribute(
                AttributeEntity(
                    id = 0,
                    characterId = charId,
                    name = text,
                    value = 10
                )
            )
        }
    }
}