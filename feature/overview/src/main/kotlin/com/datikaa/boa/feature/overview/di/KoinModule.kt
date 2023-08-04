package com.datikaa.boa.feature.overview.di

import com.datikaa.boa.feature.overview.domain.FlowCharacterUseCase
import com.datikaa.boa.feature.overview.ui.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val overviewKoinModule = module {
    viewModelOf(::OverviewViewModel)

    factoryOf(::FlowCharacterUseCase)
}
