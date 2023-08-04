package com.datikaa.bookofadventurers.feature.overview.di

import com.datikaa.bookofadventurers.feature.overview.domain.FlowCharacterUseCase
import com.datikaa.bookofadventurers.feature.overview.ui.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val overviewKoinModule = module {
    viewModelOf(::OverviewViewModel)

    factoryOf(::FlowCharacterUseCase)
}
