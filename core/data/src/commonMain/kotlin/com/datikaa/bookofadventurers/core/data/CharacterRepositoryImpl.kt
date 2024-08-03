package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.data.adapter.ability.mapToEntity
import com.datikaa.bookofadventurers.core.data.adapter.character.toDomain
import com.datikaa.bookofadventurers.core.data.adapter.character.toEntity
import com.datikaa.bookofadventurers.core.database.crossref.CharacterBackgroundCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterClassCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterSelectedClassModifierCrossRef
import com.datikaa.bookofadventurers.core.database.dao.AbilityDao
import com.datikaa.bookofadventurers.core.database.dao.CharacterDao
import com.datikaa.bookofadventurers.core.database.dao.ClassDao
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class CharacterRepositoryImpl(
    private val abilityDao: AbilityDao,
    private val characterDao: CharacterDao,
    private val classDao: ClassDao,
    private val modifierRepository: ModifierRepository,
) : CharacterRepository {

    override fun flowListOfCharacters(): Flow<List<BoaCharacter>> = characterDao
        .flowCharacters()
        .map { flow ->  flow.map { it.toDomain() } }
        .flowOn(Dispatchers.IO)

    override fun flowCharacter(id: Int): Flow<BoaCharacter> = characterDao
        .flowCharacterWithAbilitiesAndModifiers(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)


    override suspend fun updateCharacter(character: BoaCharacter) =
        characterDao.updateCharacter(character.toEntity())


    override suspend fun insertCharacter(character: BoaCharacter): Long {
        val id = characterDao.insertCharacter(character.toEntity())
        characterDao.insertCharacterClassCrossRef(
            CharacterClassCrossRef(
                characterId = id,
                classId = character.characterClass.id
            )
        )
        characterDao.insertCharacterBackgroundCrossRef(
            CharacterBackgroundCrossRef(
                characterId = id,
                backgroundId = character.characterBackground.id
            )
        )
        abilityDao.insertOrUpdateAbility(character.abilityList.mapToEntity(id.toInt()))
        return id
    }

    override suspend fun linkCharacterWithSelectedModifiers(charId: Long, modifierIds: List<Long>) {
        characterDao.insertCharacterSelectedClassModifierCrossRefs(modifierIds.map {
            CharacterSelectedClassModifierCrossRef(charId, it)
        })
    }

    override suspend fun clearAll() {
        abilityDao.deleteAllAbilities()
        characterDao.deleteCharacters()
        classDao.deleteClasses()
        modifierRepository.deleteAllModifiers()
    }
}
