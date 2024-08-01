package com.datikaa.bookofadventurers.core.database.di

import com.datikaa.bookofadventurers.core.database.BoaDatabase
import com.datikaa.bookofadventurers.core.database.roomDatabaseFactory
import org.koin.dsl.module

val databaseKoinModule = module {
    roomDatabaseFactory()

    factory { get<BoaDatabase>().abilityDao() }
    factory { get<BoaDatabase>().classDao() }
    factory { get<BoaDatabase>().characterDao() }
    factory { get<BoaDatabase>().backgroundDao() }
    factory { get<BoaDatabase>().modifierDao() }
}
