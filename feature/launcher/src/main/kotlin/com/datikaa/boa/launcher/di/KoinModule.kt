package com.datikaa.boa.launcher.di

import com.datikaa.boa.launcher.domain.ClearEverythingUseCase
import com.datikaa.boa.launcher.domain.FlowAllModifiersUseCase
import com.datikaa.boa.launcher.domain.FlowCharacterNamesUseCase
import com.datikaa.boa.launcher.ui.LauncherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val launcherKoinModule = module {
    viewModelOf(::LauncherViewModel)

    factoryOf(::ClearEverythingUseCase)
    factoryOf(::FlowCharacterNamesUseCase)
    factoryOf(::FlowAllModifiersUseCase)
}
