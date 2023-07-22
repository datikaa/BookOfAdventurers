package com.datikaa.charlatan.modifier.di

import com.datikaa.charlatan.modifier.domain.CreateNewModifierUseCase
import com.datikaa.charlatan.modifier.domain.FlowAllModifiersUseCase
import com.datikaa.charlatan.modifier.ui.ModifierViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val modifierKoinModule = module {
    viewModelOf(::ModifierViewModel)

    factoryOf(::FlowAllModifiersUseCase)
    factoryOf(::CreateNewModifierUseCase)
}
