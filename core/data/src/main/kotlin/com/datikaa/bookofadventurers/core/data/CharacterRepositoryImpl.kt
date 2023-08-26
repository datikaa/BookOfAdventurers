package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.data.adapter.toRealm
import com.datikaa.bookofadventurers.core.data.adapter.mapToDomain
import com.datikaa.bookofadventurers.core.data.adapter.toDomain
import com.datikaa.bookofadventurers.core.data.adapter.toProficiencyRealm
import com.datikaa.bookofadventurers.core.data.adapter.toScoreRealm
import com.datikaa.bookofadventurers.core.database.realm.RealmCharacter
import com.datikaa.bookofadventurers.core.database.realm.RealmPlayerClass
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CharacterRepositoryImpl(
    private val realm: Realm,
) : CharacterRepository {

    override fun flowListOfCharacters(): Flow<List<BoaCharacter>> = realm
        .query<RealmCharacter>()
        .asFlow()
        .map { it.list.mapToDomain() }

    override fun flowCharacter(id: Int): Flow<BoaCharacter> = realm
        .query<RealmCharacter>("_id == $0", id)
        .asFlow()
        .map { it.list.firstOrNull()?.toDomain() ?: throw IllegalArgumentException("Missing character for id") }

    override suspend fun updateCharacter(character: BoaCharacter): Unit = realm.write {
        val realmCharacter = query<RealmCharacter>("_id == $0", character.id).first().find()
            ?: throw IllegalArgumentException("Missing character for id")
        val realmClass = query<RealmPlayerClass>("_id == $0", character.boaClass.id).first().find()
            ?: throw IllegalArgumentException("Missing class for id")
        realmCharacter.apply {
            name = character.name
            level = character.level
            boaClass = realmClass
            abilityList = character.abilityList.map { it.toRealm() }.toRealmList()
            scoreModifiers = character.modifiers.toScoreRealm()
            proficiencyModifiers = character.modifiers.toProficiencyRealm()
        }
    }

    override suspend fun insertCharacter(character: BoaCharacter): Long = realm.write {
        var lastRealmCharacterId =
            query<RealmCharacter>().sort("_id", Sort.DESCENDING).first().find()?._id ?: -1
        val realmClass = query<RealmPlayerClass>("_id == $0", character.boaClass.id).first().find()
            ?: throw IllegalArgumentException("Missing class for id")
        val realmCharacter = RealmCharacter().apply {
            _id = lastRealmCharacterId++
            name = character.name
            level = character.level
            boaClass = realmClass
            abilityList = character.abilityList.map { it.toRealm() }.toRealmList()
            scoreModifiers = character.modifiers.toScoreRealm()
            proficiencyModifiers = character.modifiers.toProficiencyRealm()
        }
        copyToRealm(realmCharacter)
        realmCharacter._id.toLong()
    }

    override suspend fun clearAll() {
        realm.write {
            deleteAll()
        }
    }
}
