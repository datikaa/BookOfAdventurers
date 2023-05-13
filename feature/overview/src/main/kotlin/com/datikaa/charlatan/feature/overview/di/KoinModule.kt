package com.datikaa.charlatan.feature.overview.di

import com.datikaa.charlatan.feature.overview.domain.ClearEverythingUseCase
import com.datikaa.charlatan.feature.overview.domain.FlowListOfAttributesUseCase
import com.datikaa.charlatan.feature.overview.ui.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val overviewKoinModule = module {
    viewModelOf(::OverviewViewModel)

    factoryOf(::ClearEverythingUseCase)
    factoryOf(::FlowListOfAttributesUseCase)
}