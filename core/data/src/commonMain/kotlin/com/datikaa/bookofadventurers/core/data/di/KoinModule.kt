package com.datikaa.bookofadventurers.core.data.di

import com.datikaa.bookofadventurers.core.data.AbilityRepository
import com.datikaa.bookofadventurers.core.data.AbilityRepositoryImpl
import com.datikaa.bookofadventurers.core.data.BackgroundRepository
import com.datikaa.bookofadventurers.core.data.BackgroundRepositoryImpl
import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.data.CharacterRepositoryImpl
import com.datikaa.bookofadventurers.core.data.ClassesRepository
import com.datikaa.bookofadventurers.core.data.ClassesRepositoryImpl
import com.datikaa.bookofadventurers.core.data.ModifierRepository
import com.datikaa.bookofadventurers.core.data.ModifierRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::AbilityRepositoryImpl) bind AbilityRepository::class
    singleOf(::BackgroundRepositoryImpl) bind BackgroundRepository::class
    singleOf(::ClassesRepositoryImpl) bind ClassesRepository::class
    singleOf(::CharacterRepositoryImpl) bind CharacterRepository::class
    singleOf(::ModifierRepositoryImpl) bind ModifierRepository::class
}
