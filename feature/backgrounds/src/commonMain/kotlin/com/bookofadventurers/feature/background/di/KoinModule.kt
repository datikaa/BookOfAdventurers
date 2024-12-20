package com.bookofadventurers.feature.background.di

import com.bookofadventurers.feature.background.domain.AddNewBackgroundUseCase
import com.bookofadventurers.feature.background.domain.FlowAllBackgroundsUseCase
import com.bookofadventurers.feature.background.domain.FlowAllModifiersUseCase
import com.bookofadventurers.feature.background.ui.BackgroundsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val backgroundsKoinModule = module {
    viewModelOf(::BackgroundsViewModel)

    factoryOf(::AddNewBackgroundUseCase)
    factoryOf(::FlowAllBackgroundsUseCase)
    factoryOf(::FlowAllModifiersUseCase)
}
