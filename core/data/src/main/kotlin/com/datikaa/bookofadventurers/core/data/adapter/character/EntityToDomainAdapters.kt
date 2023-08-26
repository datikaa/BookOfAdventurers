package com.datikaa.bookofadventurers.core.data.adapter.character

import com.datikaa.bookofadventurers.core.data.adapter.classes.toDomain
import com.datikaa.bookofadventurers.core.data.adapter.modifier.toDomain
import com.datikaa.bookofadventurers.core.database.entity.AbilityEntity
import com.datikaa.bookofadventurers.core.database.realm.RealmCharacter
import com.datikaa.bookofadventurers.core.database.realm.RealmPlayerAbility
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import io.realm.kotlin.query.RealmResults

@JvmName("mapCharacterEntityToDomain")
internal fun RealmResults<RealmCharacter>.mapToDomain() = map { it.toDomain() }

internal fun RealmCharacter.toDomain(): BoaCharacter = BoaCharacter(
    id = _id,
    name = name,
    level = level,
    boaClass = boaClass?.toDomain()
        ?: throw IllegalStateException("Character without class should not exist"),
    abilityList = abilityList.map { realmPlayerAbility -> realmPlayerAbility.toDomain() },
    modifiers = proficiencyModifiers.toDomain() + scoreModifiers.toDomain(),
)

internal fun Ability.toEntityEnum(): AbilityEntity.Type = when (this) {
    is Ability.Charisma -> AbilityEntity.Type.Charisma
    is Ability.Constitution -> AbilityEntity.Type.Constitution
    is Ability.Dexterity -> AbilityEntity.Type.Dexterity
    is Ability.Intelligence -> AbilityEntity.Type.Intelligence
    is Ability.Strength -> AbilityEntity.Type.Strength
    is Ability.Wisdom -> AbilityEntity.Type.Wisdom
}

internal fun RealmPlayerAbility.toDomain(): Ability = when (AbilityEntity.Type.entries[type]) {
    AbilityEntity.Type.Strength -> Ability.Strength(value)
    AbilityEntity.Type.Dexterity -> Ability.Dexterity(value)
    AbilityEntity.Type.Constitution -> Ability.Constitution(value)
    AbilityEntity.Type.Intelligence -> Ability.Intelligence(value)
    AbilityEntity.Type.Wisdom -> Ability.Wisdom(value)
    AbilityEntity.Type.Charisma -> Ability.Charisma(value)
}
