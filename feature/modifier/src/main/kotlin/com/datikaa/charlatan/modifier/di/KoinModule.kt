package com.datikaa.charlatan.modifier.di

import com.datikaa.charlatan.modifier.ui.ModifierViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val modifierKoinModule = module {
    viewModelOf(::ModifierViewModel)
}