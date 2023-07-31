package com.datikaa.core.data

import com.datikaa.charlatan.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.charlatan.core.database.dao.ModifierDao
import com.datikaa.charlatan.core.domain.Modifier
import com.datikaa.core.data.adapter.modifier.toDomain
import com.datikaa.core.data.adapter.modifier.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ModifierRepositoryImpl(
    private val modifierDao: ModifierDao,
) : ModifierRepository {

    override suspend fun insertModifier(modifier: Modifier): Long {
        return modifierDao.insertModifier(modifier.toEntity())
    }

    override suspend fun associateModifierWithCharacter(modifierId: Long, characterId: Long) {
        modifierDao.insertCharacterModifierCrossRef(
            CharacterModifierCrossRef(
                characterId = characterId,
                modifierId = modifierId,
            )
        )
    }

    override suspend fun deleteAllModifiers() {
        modifierDao.deleteAllModifiers()
    }

    override fun flowAllModifiers(): Flow<List<Modifier>> =
        modifierDao.flowModifiers().map { modifierEntities ->
            modifierEntities.map { modifierEntity -> modifierEntity.toDomain() }
        }

    override suspend fun getModifier(id: Int): Modifier = modifierDao.getModifier(id).toDomain()

    override suspend fun getCharacterModifierCrossRef(
        modifierId: Long,
        characterId: Long
    ): Pair<Long, Long>? {
        return modifierDao.getCharacterModifierCrossRef(
            characterId = characterId,
            modifierId = modifierId,
        )?.let { crossRef ->
            crossRef.characterId to crossRef.modifierId
        }
    }

    override suspend fun removeCharacterModifierCrossRef(
        modifierId: Long,
        characterId: Long
    ) {
        modifierDao.removeCharacterModifierCrossRef(modifierId, characterId)
    }
}
