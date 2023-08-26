package com.datikaa.bookofadventurers.core.database.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

class RealmCharacter : RealmObject {
    @PrimaryKey
    var _id: Int = -1
    @Index
    var name: String = ""
    var level: Int = -1
    var boaClass: RealmPlayerClass? = null
    var abilityList: RealmList<RealmPlayerAbility> = realmListOf()
    var scoreModifiers: RealmList<RealmScoreModifier> = realmListOf()
    var proficiencyModifiers: RealmList<RealmProficiencyModifier> = realmListOf()
}

class RealmPlayerAbility : EmbeddedRealmObject {
    var type: Int = -1
    var value: Int = -1
}

class RealmPlayerClass : RealmObject {
    @PrimaryKey
    var _id: Int = -1
    var name: String = ""
    var scoreModifiers: RealmList<RealmScoreModifier> = realmListOf()
    var proficiencyModifiers: RealmList<RealmProficiencyModifier> = realmListOf()
}

class RealmScoreModifier : RealmObject {
    @PrimaryKey
    var _id: Int = -1
    var name: String = ""
    var description: String = ""
    var modifiableScoreType: Int = -1
    var modifierValue: Int = -1
}

class RealmProficiencyModifier : RealmObject {
    @PrimaryKey
    var _id: Int = -1
    var name: String = ""
    var description: String = ""
    var proficiencyType: Int = -1
}

internal val realmSchema = setOf(
    RealmCharacter::class,
    RealmPlayerAbility::class,
    RealmPlayerClass::class,
    RealmScoreModifier::class,
    RealmProficiencyModifier::class,
)
