package com.datikaa.boa.core.data

import com.datikaa.boa.core.domain.Ability

interface AbilityRepository {
    suspend fun updateAbility(characterId: Int, ability: Ability)
}
