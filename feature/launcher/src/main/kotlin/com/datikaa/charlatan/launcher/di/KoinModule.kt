package com.datikaa.charlatan.launcher.di

import com.datikaa.charlatan.launcher.FlowCharacterNamesUseCase
import com.datikaa.charlatan.launcher.ui.LauncherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val launcherKoinModule = module {
    viewModelOf(::LauncherViewModel)

    factoryOf(::FlowCharacterNamesUseCase)
}
