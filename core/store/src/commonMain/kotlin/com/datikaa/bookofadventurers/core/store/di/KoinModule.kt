package com.datikaa.bookofadventurers.core.store.di

import com.datikaa.bookofadventurers.core.store.CharacterStore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storeModule = module {
    pathProvider()
    singleOf(::CharacterStore)
}
