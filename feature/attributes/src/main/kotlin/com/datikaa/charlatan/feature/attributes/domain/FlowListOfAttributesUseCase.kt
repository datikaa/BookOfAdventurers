package com.datikaa.charlatan.feature.attributes.domain

import com.datikaa.charlatan.core.database.CharAttribute
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FlowListOfAttributesUseCase(
    private val database: CmmDatabase,
) {

    operator fun invoke(): Flow<List<Attribute>> =
        database.characterDao().getAttributes().map { list ->
            list.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)

    private fun CharAttribute.toDomain() = Attribute(
        id = charAttributeId, name = name, value = value,
    )
}