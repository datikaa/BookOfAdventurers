package com.datikaa.boa.core.data.di

import com.datikaa.boa.core.data.AbilityRepository
import com.datikaa.boa.core.data.AbilityRepositoryImpl
import com.datikaa.boa.core.data.CharacterRepositoryImpl
import com.datikaa.boa.core.data.CharacterRepository
import com.datikaa.boa.core.data.ModifierRepository
import com.datikaa.boa.core.data.ModifierRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::AbilityRepositoryImpl) bind AbilityRepository::class
    singleOf(::CharacterRepositoryImpl) bind CharacterRepository::class
    singleOf(::ModifierRepositoryImpl) bind ModifierRepository::class
}
