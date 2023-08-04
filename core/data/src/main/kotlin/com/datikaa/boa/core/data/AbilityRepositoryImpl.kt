package com.datikaa.boa.core.data

import com.datikaa.boa.core.database.dao.AbilityDao
import com.datikaa.boa.core.domain.Ability
import com.datikaa.boa.core.data.adapter.ability.toEntityEnum
import com.datikaa.boa.core.data.adapter.ability.toPartialUpdate

internal class AbilityRepositoryImpl(
    private val abilityDao: AbilityDao,
) : AbilityRepository {
    override suspend fun updateAbility(characterId: Int, ability: Ability) {
        val abilityEntity = abilityDao.getAbility(characterId, ability.toEntityEnum())
        abilityDao.updateAbility(ability.toPartialUpdate(abilityEntity.id))
    }
}
