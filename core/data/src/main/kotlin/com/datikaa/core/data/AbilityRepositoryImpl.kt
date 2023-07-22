package com.datikaa.core.data

import com.datikaa.charlatan.core.database.dao.AbilityDao
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.core.data.adapter.ability.toEntityEnum
import com.datikaa.core.data.adapter.ability.toPartialUpdate

class AbilityRepositoryImpl(
    private val abilityDao: AbilityDao,
) : AbilityRepository {
    override suspend fun updateAbility(characterId: Int, ability: Ability) {
        val abilityEntity = abilityDao.getAbility(characterId, ability.toEntityEnum())
        abilityDao.updateAbility(ability.toPartialUpdate(abilityEntity.id))
    }
}