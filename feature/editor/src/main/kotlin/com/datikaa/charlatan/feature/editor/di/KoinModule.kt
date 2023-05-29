package com.datikaa.charlatan.feature.editor.di

import com.datikaa.charlatan.feature.editor.domain.AddCharacterUseCase
import com.datikaa.charlatan.feature.editor.domain.FlowListAllCharactersUseCase
import com.datikaa.charlatan.feature.editor.ui.CharactersScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val characterKoinModule = module {
    viewModelOf(::CharactersScreenViewModel)

    factoryOf(::AddCharacterUseCase)
    factoryOf(::FlowListAllCharactersUseCase)
}