package com.datikaa.boa.modifier.di

import com.datikaa.boa.modifier.domain.CreateNewModifierUseCase
import com.datikaa.boa.modifier.domain.FlowAllModifiersUseCase
import com.datikaa.boa.modifier.ui.ModifierViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val modifierKoinModule = module {
    viewModelOf(::ModifierViewModel)

    factoryOf(::FlowAllModifiersUseCase)
    factoryOf(::CreateNewModifierUseCase)
}
