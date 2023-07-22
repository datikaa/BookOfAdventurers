package com.datikaa.core.data

import com.datikaa.charlatan.core.database.dao.AttributeDao
import com.datikaa.charlatan.core.database.dao.CharacterDao
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.core.data.adapter.mapToDomain
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

    override fun flowListOfCharacters(): Flow<List<Character>> = characterDao
        .flowCharacters()
        .map { it.mapToDomain() }
        .flowOn(Dispatchers.IO)

    override fun flowCharacter(id: Int): Flow<Character> = characterDao
        .flowCharacterWithAttributes(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)


    override suspend fun updateCharacter(character: Character) = withContext(Dispatchers.IO) {
        characterDao.updateCharacter(character.toEntity())
    }

    override suspend fun insertCharacter(character: Character) = withContext(Dispatchers.IO) {
        val id = characterDao.insertCharacter(character.toEntity())
        characterDao.insertOrUpdateAttributes(character.abilityList.mapToEntity(id.toInt()))
        return@withContext id
    }

    override suspend fun clearAll() = withContext(Dispatchers.IO) {
        attributesDao.deleteAttributes()
        characterDao.deleteCharacters()
    }
}
