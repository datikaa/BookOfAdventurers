package com.datikaa.bookofadventurers.modifier.di

import com.datikaa.bookofadventurers.modifier.domain.CreateNewModifierUseCase
import com.datikaa.bookofadventurers.modifier.domain.FlowAllModifiersUseCase
import com.datikaa.bookofadventurers.modifier.ui.ModifierViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val modifierKoinModule = module {
    viewModelOf(::ModifierViewModel)

    factoryOf(::FlowAllModifiersUseCase)
    factoryOf(::CreateNewModifierUseCase)
}
