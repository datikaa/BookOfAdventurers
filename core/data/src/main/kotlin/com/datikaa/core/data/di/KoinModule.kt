package com.datikaa.core.data.di

import com.datikaa.core.data.AbilityRepository
import com.datikaa.core.data.AbilityRepositoryImpl
import com.datikaa.core.data.CharacterRepositoryImpl
import com.datikaa.core.data.CharacterRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::AbilityRepositoryImpl) bind AbilityRepository::class
    singleOf(::CharacterRepositoryImpl) bind CharacterRepository::class
}