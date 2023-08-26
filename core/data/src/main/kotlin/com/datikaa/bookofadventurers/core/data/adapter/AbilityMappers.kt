package com.datikaa.bookofadventurers.core.data.adapter

import com.datikaa.bookofadventurers.core.database.realm.RealmPlayerAbility
import com.datikaa.bookofadventurers.core.domain.Ability

internal fun Ability.toRealm(): RealmPlayerAbility = RealmPlayerAbility().apply {
    type = this@toRealm.toEntityEnum().ordinal
    value = this@toRealm.value
}
