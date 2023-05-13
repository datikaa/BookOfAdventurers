package com.datikaa.charlatan.feature.attributes.domain

import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IncreaseValueOfAttributeUseCase(
    private val database: CmmDatabase,
) {

    suspend operator fun invoke(attribute: Attribute, character: Character) {
        withContext(Dispatchers.IO) {
            database.attributesDao().updateAttribute(
                AttributeEntity(
                    id = attribute.id,
                    characterId = character.id,
                    name = attribute.name,
                    value = attribute.value + 1,
                )
            )
        }
    }
}