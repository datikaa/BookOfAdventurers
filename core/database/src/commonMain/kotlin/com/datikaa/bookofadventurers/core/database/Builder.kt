package com.datikaa.bookofadventurers.core.database

import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

expect fun Module.roomDatabaseFactory(): KoinDefinition<BoaDatabase>
