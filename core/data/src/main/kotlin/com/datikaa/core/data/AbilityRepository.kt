package com.datikaa.core.data

import com.datikaa.charlatan.core.domain.Ability

interface AbilityRepository {
    suspend fun updateAbility(characterId: Int, ability: Ability)
}