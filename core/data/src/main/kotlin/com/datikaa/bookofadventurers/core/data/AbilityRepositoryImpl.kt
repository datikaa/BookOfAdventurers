package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.data.adapter.character.toEntityEnum
import com.datikaa.bookofadventurers.core.database.realm.RealmCharacter
import com.datikaa.bookofadventurers.core.domain.Ability
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

internal class AbilityRepositoryImpl(
    private val realm: Realm,
) : AbilityRepository {
    override suspend fun updateAbility(characterId: Int, ability: Ability): Unit = realm.write {
        val realmCharacter = query<RealmCharacter>("_id == $0", characterId).first().find()
            ?: throw IllegalStateException("Missing character for id")
        realmCharacter.abilityList.first { ability.toEntityEnum().ordinal == it.type }.value = ability.value
    }
}
