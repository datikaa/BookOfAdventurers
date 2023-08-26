package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.data.adapter.classes.toDomain
import com.datikaa.bookofadventurers.core.database.realm.RealmPlayerClass
import com.datikaa.bookofadventurers.core.domain.BoaClass
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClassesRepositoryImpl(
    private val realm: Realm,
) : ClassesRepository {

    override fun flowAllClasses(): Flow<List<BoaClass>> = realm
        .query<RealmPlayerClass>()
        .asFlow()
        .map { it.list.map { realmClass -> realmClass.toDomain() } }
}
