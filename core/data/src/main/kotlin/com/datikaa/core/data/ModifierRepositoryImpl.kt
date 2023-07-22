package com.datikaa.core.data

import com.datikaa.charlatan.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.charlatan.core.database.dao.ModifierDao
import com.datikaa.charlatan.core.domain.Modifier
import com.datikaa.core.data.adapter.modifier.toEntity

class ModifierRepositoryImpl(
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
}