package com.datikaa.bookofadventurers.core.data.adapter

import com.datikaa.bookofadventurers.core.database.realm.RealmCharacter
import com.datikaa.bookofadventurers.core.database.realm.RealmPlayerAbility
import com.datikaa.bookofadventurers.core.database.realm.AbilityType
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

internal fun Ability.toEntityEnum(): AbilityType = when (this) {
    is Ability.Charisma -> AbilityType.Charisma
    is Ability.Constitution -> AbilityType.Constitution
    is Ability.Dexterity -> AbilityType.Dexterity
    is Ability.Intelligence -> AbilityType.Intelligence
    is Ability.Strength -> AbilityType.Strength
    is Ability.Wisdom -> AbilityType.Wisdom
}

internal fun RealmPlayerAbility.toDomain(): Ability = when (AbilityType.entries[type]) {
    AbilityType.Strength -> Ability.Strength(value)
    AbilityType.Dexterity -> Ability.Dexterity(value)
    AbilityType.Constitution -> Ability.Constitution(value)
    AbilityType.Intelligence -> Ability.Intelligence(value)
    AbilityType.Wisdom -> Ability.Wisdom(value)
    AbilityType.Charisma -> Ability.Charisma(value)
}


