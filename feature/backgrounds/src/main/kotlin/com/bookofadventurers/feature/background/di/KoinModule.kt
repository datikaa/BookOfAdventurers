package com.bookofadventurers.feature.background.di

import com.bookofadventurers.feature.background.domain.FlowAllBackgroundsUseCase
import com.bookofadventurers.feature.background.ui.BackgroundsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val backgroundsKoinModule = module {
    viewModelOf(::BackgroundsViewModel)

    factoryOf(::FlowAllBackgroundsUseCase)
}
