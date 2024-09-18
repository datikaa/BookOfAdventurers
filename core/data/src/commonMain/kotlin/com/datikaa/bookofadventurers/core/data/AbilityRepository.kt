package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.domain.Ability

interface AbilityRepository {
    suspend fun updateAbility(characterId: Int, ability: Ability)
}
