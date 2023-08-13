package com.datikaa.bookofadventurers.launcher.di

import com.datikaa.bookofadventurers.launcher.domain.ClearEverythingUseCase
import com.datikaa.bookofadventurers.launcher.domain.FlowAllClassesUseCase
import com.datikaa.bookofadventurers.launcher.domain.FlowAllModifiersUseCase
import com.datikaa.bookofadventurers.launcher.domain.FlowCharacterNamesUseCase
import com.datikaa.bookofadventurers.launcher.ui.LauncherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val launcherKoinModule = module {
    viewModelOf(::LauncherViewModel)

    factoryOf(::ClearEverythingUseCase)
    factoryOf(::FlowCharacterNamesUseCase)
    factoryOf(::FlowAllModifiersUseCase)
    factoryOf(::FlowAllClassesUseCase)
}
