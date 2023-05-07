package com.datikaa.charlatan.feature.overview.di

import com.datikaa.charlatan.feature.overview.ui.OverviewViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val overviewKoinModule = module {
    factoryOf(::OverviewViewModel)
}