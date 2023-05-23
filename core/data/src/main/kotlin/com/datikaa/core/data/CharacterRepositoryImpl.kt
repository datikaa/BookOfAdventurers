package com.datikaa.core.data

import com.datikaa.charlatan.core.database.dao.AttributeDao
import com.datikaa.charlatan.core.database.dao.CharacterDao
import com.datikaa.charlatan.core.database.entity.CharacterWithAttributes
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.core.data.adapter.mapToEntity
import com.datikaa.core.data.adapter.toDomain
import com.datikaa.core.data.adapter.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val attributesDao: AttributeDao,
    private val characterDao: CharacterDao,
) : CharacterRepository {

    override fun flowCharacter(id: Int): Flow<Character> = characterDao
        .flowCharacterWithAttributes(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)

    override suspend fun createOrUpdateCharacter(character: Character) =
        withContext(Dispatchers.IO) {
            val attributes = characterDao.getAttributesForCharacter(character.id)
            characterDao.insertOrUpdateCharacterWithAttributes(
                characterWithAttributes = CharacterWithAttributes(
                    characterEntity = character.toEntity(),
                    attributes = character.abilityList.mapToEntity(character.id, attributes)
                )
            )
        }

    override suspend fun clearAll() = withContext(Dispatchers.IO) {
        attributesDao.deleteAttributes()
        characterDao.deleteCharacter()
    }
}