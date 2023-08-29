package com.bookofadventurers.feature.wizard.di

import com.bookofadventurers.feature.wizard.ui.WizardViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val wizardKoinModule = module {
    viewModelOf(::WizardViewModel)
}
