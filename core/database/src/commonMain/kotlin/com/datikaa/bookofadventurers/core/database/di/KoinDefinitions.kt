package com.datikaa.bookofadventurers.core.database.di

import com.datikaa.bookofadventurers.core.database.BoaDatabase
import com.datikaa.bookofadventurers.core.database.prefill.JsonResourceFunctions
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

expect fun Module.roomDatabaseSingle(): KoinDefinition<BoaDatabase>

expect fun Module.jsonResourceFunctionsFactory(): KoinDefinition<JsonResourceFunctions>
