package com.bookofadventurers.feature.wizard.di

import com.bookofadventurers.feature.wizard.domain.AddCharacterUseCase
import com.bookofadventurers.feature.wizard.domain.GetListOfBackgroundsUseCase
import com.bookofadventurers.feature.wizard.domain.GetListOfClassesUseCase
import com.bookofadventurers.feature.wizard.ui.WizardViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val wizardKoinModule = module {
    viewModelOf(::WizardViewModel)

    factoryOf(::AddCharacterUseCase)
    factoryOf(::GetListOfClassesUseCase)
    factoryOf(::GetListOfBackgroundsUseCase)
}
