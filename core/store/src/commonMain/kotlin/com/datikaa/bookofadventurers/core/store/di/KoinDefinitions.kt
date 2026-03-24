package com.datikaa.bookofadventurers.core.store.di

import com.datikaa.bookofadventurers.core.store.PathProvider
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

expect fun Module.pathProvider(): KoinDefinition<PathProvider>
