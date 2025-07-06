package com.datikaa.bookofadventurers.core.data.di

import com.datikaa.bookofadventurers.core.data.AbilityRepository
import com.datikaa.bookofadventurers.core.data.AbilityRepository_KStoreImpl
import com.datikaa.bookofadventurers.core.data.BackgroundRepository
import com.datikaa.bookofadventurers.core.data.BackgroundRepositoryImpl
import com.datikaa.bookofadventurers.core.data.CharacterRepository
import com.datikaa.bookofadventurers.core.data.CharacterRepository_KStoreImpl
import com.datikaa.bookofadventurers.core.data.ClassesRepository
import com.datikaa.bookofadventurers.core.data.ClassesRepositoryImpl
import com.datikaa.bookofadventurers.core.data.ModifierRepository
import com.datikaa.bookofadventurers.core.data.ModifierRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::BackgroundRepositoryImpl) bind BackgroundRepository::class
    singleOf(::ClassesRepositoryImpl) bind ClassesRepository::class
    singleOf(::ModifierRepositoryImpl) bind ModifierRepository::class

    singleOf(::AbilityRepository_KStoreImpl) bind AbilityRepository::class
    singleOf(::CharacterRepository_KStoreImpl) bind CharacterRepository::class
}
