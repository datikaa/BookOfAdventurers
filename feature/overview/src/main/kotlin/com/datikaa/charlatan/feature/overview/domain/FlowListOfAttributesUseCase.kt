package com.datikaa.charlatan.feature.overview.domain

import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FlowListOfAttributesUseCase(
    private val database: CmmDatabase,
) {

    operator fun invoke(): Flow<List<Attribute>> =
        database.attributesDao().getAttributes().map { list ->
            list.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)

    private fun AttributeEntity.toDomain() = Attribute(
        name = name, value = value,
    )
}