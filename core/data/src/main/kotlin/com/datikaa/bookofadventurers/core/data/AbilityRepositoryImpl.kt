package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.database.dao.AbilityDao
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.data.adapter.ability.toEntityEnum
import com.datikaa.bookofadventurers.core.data.adapter.ability.toPartialUpdate

internal class AbilityRepositoryImpl(
    private val abilityDao: AbilityDao,
) : AbilityRepository {
    override suspend fun updateAbility(characterId: Int, ability: Ability) {
        val abilityEntity = abilityDao.getAbility(characterId, ability.toEntityEnum())
        abilityDao.updateAbility(ability.toPartialUpdate(abilityEntity.id))
    }
}
