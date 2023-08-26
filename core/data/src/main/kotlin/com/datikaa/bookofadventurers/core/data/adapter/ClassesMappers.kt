package com.datikaa.bookofadventurers.core.data.adapter

import com.datikaa.bookofadventurers.core.database.realm.RealmPlayerClass
import com.datikaa.bookofadventurers.core.domain.BoaClass

internal fun BoaClass.toRealm() = RealmPlayerClass().apply {
    name = this@toRealm.name
    scoreModifiers = modifiers.toScoreRealm()
    proficiencyModifiers = modifiers.toProficiencyRealm()
}

internal fun RealmPlayerClass.toDomain() = BoaClass(
    id = _id.toLong(),
    name = name,
    modifiers = proficiencyModifiers.toDomain() + scoreModifiers.toDomain()
)
