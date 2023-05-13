package com.datikaa.charlatan.feature.attributes.domain

import com.datikaa.charlatan.core.database.CmmDatabase
import com.datikaa.charlatan.core.database.entity.CharacterWithAttributes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FlowListOfCharacterAttributesUseCase(
    private val database: CmmDatabase,
) {

    operator fun invoke(character: Character): Flow<List<Attribute>> =
        database.characterDao().getCharacterWithAttributes(character.id).map {
            it.toDomainAttributes()
        }.flowOn(Dispatchers.IO)

    private fun CharacterWithAttributes.toDomainAttributes() = attributes.map {
        Attribute(id = it.id, name = it.name, value = it.value)
    }
}