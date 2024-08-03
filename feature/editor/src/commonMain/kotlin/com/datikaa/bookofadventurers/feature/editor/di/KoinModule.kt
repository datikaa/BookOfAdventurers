package com.datikaa.bookofadventurers.feature.editor.di

import com.datikaa.bookofadventurers.feature.editor.domain.*
import com.datikaa.bookofadventurers.feature.editor.ui.CharactersScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val editorKoinModule = module {
    viewModelOf(::CharactersScreenViewModel)

    factoryOf(::FlowAllModifiersUseCase)
    factoryOf(::FlowAllClassesUseCase)
    factoryOf(::FlowCharacterUseCase)
    factoryOf(::FlowListAllCharactersUseCase)
    factoryOf(::ModifyAbilityValueOfCharacterUseCase)
    factoryOf(::ModifyLevelOfCharacterUseCase)
    factoryOf(::AddOrRemoveModifierToCharacterUseCase)
    factoryOf(::AddOrRemoveModifierToCharacterUseCase)
    factoryOf(::FlowSelectedModifiersForCharacterUseCase)
}
