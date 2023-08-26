package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.data.adapter.modifier.toDomain
import com.datikaa.bookofadventurers.core.data.adapter.modifier.toEntityEnum
import com.datikaa.bookofadventurers.core.database.realm.RealmCharacter
import com.datikaa.bookofadventurers.core.database.realm.RealmProficiencyModifier
import com.datikaa.bookofadventurers.core.database.realm.RealmScoreModifier
import com.datikaa.bookofadventurers.core.domain.Modifier
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlin.math.max

internal class ModifierRepositoryImpl(
    private val realm: Realm,
) : ModifierRepository {

    override suspend fun insertModifier(modifier: Modifier): Long = realm.write {
        val lastScoreModifierId =
            query<RealmScoreModifier>().sort("_id", Sort.DESCENDING).first().find()?._id ?: -1
        val lastProficiencyModifierId =
            query<RealmProficiencyModifier>().sort("_id", Sort.DESCENDING).first().find()?._id ?: -1
        val id = max(lastScoreModifierId, lastProficiencyModifierId) + 1
        val realmObj = when (modifier) {
            is Modifier.Holder -> throw IllegalArgumentException("Deprecated")
            is Modifier.Proficiency -> RealmProficiencyModifier().apply {
                _id = id
                name = modifier.name
                description = modifier.description
                proficiencyType = modifier.proficiencyType.toEntityEnum().ordinal
            }

            is Modifier.Score -> RealmScoreModifier().apply {
                _id = id
                name = modifier.name
                description = modifier.description
                modifiableScoreType = modifier.modifiableScoreType.toEntityEnum().ordinal
                modifierValue = modifier.value
            }
        }
        copyToRealm(realmObj)
        return@write id.toLong()
    }

    @Deprecated("Use with Modifier")
    override suspend fun associateModifierWithCharacter(modifierId: Long, characterId: Long): Unit =
        realm.write {
            val realmCharacter = query<RealmCharacter>("_id == $0", characterId).first().find()
                ?: throw IllegalArgumentException("Missing character for id")
            query<RealmProficiencyModifier>("_id == $0", modifierId).first().find()
                ?.let { realmProficiencyModifier ->
                    realmCharacter.proficiencyModifiers.add(realmProficiencyModifier)
                }
            query<RealmScoreModifier>("_id == $0", modifierId).first().find()
                ?.let { realmScoreModifier ->
                    realmCharacter.scoreModifiers.add(realmScoreModifier)
                }
        }

    override suspend fun associateModifierWithCharacter(modifier: Modifier, characterId: Long) {
        TODO("Not yet implemented")
    }

    // TODO: :puke:
    override fun flowAllModifiers(): Flow<List<Modifier>> = combine(
        realm.query<RealmScoreModifier>().asFlow()
            .map { resultsChange -> resultsChange.list.map { it.toDomain() } },
        realm.query<RealmProficiencyModifier>().asFlow()
            .map { resultsChange -> resultsChange.list.map { it.toDomain() } },
    ) { realmScoreModifier, realmProficiencyModifier ->
        realmScoreModifier + realmProficiencyModifier
    }

    override suspend fun getModifier(id: Int): Modifier = with(realm) {
        query<RealmProficiencyModifier>("_id == $0", id).first().find()?.toDomain()
            ?: query<RealmScoreModifier>("_id == $0", id).first().find()?.toDomain()
            ?: throw IllegalArgumentException("Missing modifier for id")
    }

    override suspend fun getCharacterModifierCrossRef(
        modifierId: Long,
        characterId: Long
    ): Pair<Long, Long>? {
        val realmCharacter = realm.query<RealmCharacter>("_id == $0", characterId).first().find()
            ?: throw IllegalArgumentException("Missing character for id")
        val realmModifiers =
            realmCharacter.scoreModifiers.map { it._id } + realmCharacter.proficiencyModifiers.map { it._id }
        return if (realmModifiers.contains(modifierId.toInt())) Pair(modifierId, characterId)
        else null
    }

    override suspend fun removeCharacterModifierCrossRef(
        modifierId: Long,
        characterId: Long
    ): Unit = realm.write {
        val realmCharacter = query<RealmCharacter>("_id == $0", characterId).first().find()
            ?: throw IllegalArgumentException("Missing character for id")
        realmCharacter.scoreModifiers.removeIf {
            it._id == modifierId.toInt()
        }
        realmCharacter.proficiencyModifiers.removeIf {
            it._id == modifierId.toInt()
        }
    }
}
