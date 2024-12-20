package com.datikaa.bookofadventurers.launcher.di

import com.datikaa.bookofadventurers.launcher.domain.*
import com.datikaa.bookofadventurers.launcher.ui.LauncherViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val launcherKoinModule = module {
    viewModelOf(::LauncherViewModel)

    factoryOf(::ClearEverythingUseCase)
    factoryOf(::FlowCharacterNamesUseCase)
    factoryOf(::FlowAllClassesUseCase)
    factoryOf(::FlowAllBackgroundsUseCase)
    factoryOf(::FlowAllModifiersUseCase)
}
