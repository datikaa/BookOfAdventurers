package com.datikaa.boa.core.data

import com.datikaa.boa.core.database.dao.AbilityDao
import com.datikaa.boa.core.database.dao.CharacterDao
import com.datikaa.boa.core.data.adapter.ability.mapToEntity
import com.datikaa.boa.core.data.adapter.character.mapToDomain
import com.datikaa.boa.core.data.adapter.character.toDomain
import com.datikaa.boa.core.data.adapter.character.toEntity
import com.datikaa.boa.core.domain.BoaCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class CharacterRepositoryImpl(
    private val abilityDao: AbilityDao,
    private val characterDao: CharacterDao,
    private val modifierRepository: ModifierRepository,
) : CharacterRepository {

    override fun flowListOfCharacters(): Flow<List<BoaCharacter>> = characterDao
        .flowCharacters()
        .map { it.mapToDomain() }
        .flowOn(Dispatchers.IO)

    override fun flowCharacter(id: Int): Flow<BoaCharacter> = characterDao
        .flowCharacterWithAbilitiesAndModifiers(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)


    override suspend fun updateCharacter(character: BoaCharacter) =
        characterDao.updateCharacter(character.toEntity())


    override suspend fun insertCharacter(character: BoaCharacter): Long {
        val id = characterDao.insertCharacter(character.toEntity())
        abilityDao.insertOrUpdateAbility(character.abilityList.mapToEntity(id.toInt()))
        return id
    }

    override suspend fun clearAll() {
        abilityDao.deleteAllAbilities()
        characterDao.deleteCharacters()
        modifierRepository.deleteAllModifiers()
    }
}
