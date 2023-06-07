package com.datikaa.core.data

import com.datikaa.charlatan.core.database.dao.AttributeDao
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.core.data.adapter.toEntityEnum
import com.datikaa.core.data.adapter.toPartialUpdate

class AbilityRepositoryImpl(
    private val attributeDao: AttributeDao,
) : AbilityRepository {
    override suspend fun updateAbility(characterId: Int, ability: Ability) {
        val attribute = attributeDao.getAttribute(characterId, ability.toEntityEnum())
        attributeDao.updateAttribute(ability.toPartialUpdate(attribute.id))
    }
}