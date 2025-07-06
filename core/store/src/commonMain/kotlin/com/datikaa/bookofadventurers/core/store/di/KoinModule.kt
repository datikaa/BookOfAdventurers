package com.datikaa.bookofadventurers.core.store.di

import com.datikaa.bookofadventurers.core.store.BackgroundStore
import com.datikaa.bookofadventurers.core.store.CharacterStore
import com.datikaa.bookofadventurers.core.store.ClassStore
import com.datikaa.bookofadventurers.core.store.ModifierStore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storeModule = module {
    pathProvider()
    singleOf(::BackgroundStore)
    singleOf(::CharacterStore)
    singleOf(::ClassStore)
    singleOf(::ModifierStore)
}
