package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.data.adapter.ability.toEntityEnumKStore
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.store.CharacterStore
import io.github.xxfast.kstore.extensions.map

internal class AbilityRepository_KStoreImpl(
    characterStore: CharacterStore,
) : AbilityRepository {

    private val characterStore = characterStore.store

    override suspend fun updateAbility(characterId: Int, ability: Ability) {
        characterStore.map { characterEntity ->
            if (characterEntity.id == characterId) {
                characterEntity.copy(
                    abilities = characterEntity.abilities.map { abilityEntity ->
                        if (ability.toEntityEnumKStore() == abilityEntity.type) {
                            abilityEntity.copy(value = ability.value)
                        } else abilityEntity
                    }
                )
            } else characterEntity
        }
    }
}
