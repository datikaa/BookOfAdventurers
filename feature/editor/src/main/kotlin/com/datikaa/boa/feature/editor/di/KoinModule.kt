package com.datikaa.boa.feature.editor.di

import com.datikaa.boa.feature.editor.domain.AddCharacterUseCase
import com.datikaa.boa.feature.editor.domain.AddOrRemoveModifierToCharacterUseCase
import com.datikaa.boa.feature.editor.domain.FlowAllModifiersUseCase
import com.datikaa.boa.feature.editor.domain.FlowCharacterUseCase
import com.datikaa.boa.feature.editor.domain.FlowListAllCharactersUseCase
import com.datikaa.boa.feature.editor.domain.FlowSelectedModifiersForCharacterUseCase
import com.datikaa.boa.feature.editor.domain.ModifyAbilityValueOfCharacterUseCase
import com.datikaa.boa.feature.editor.domain.ModifyLevelOfCharacterUseCase
import com.datikaa.boa.feature.editor.ui.CharactersScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val editorKoinModule = module {
    viewModelOf(::CharactersScreenViewModel)

    factoryOf(::AddCharacterUseCase)
    factoryOf(::FlowAllModifiersUseCase)
    factoryOf(::FlowCharacterUseCase)
    factoryOf(::FlowListAllCharactersUseCase)
    factoryOf(::ModifyAbilityValueOfCharacterUseCase)
    factoryOf(::ModifyLevelOfCharacterUseCase)
    factoryOf(::AddOrRemoveModifierToCharacterUseCase)
    factoryOf(::AddOrRemoveModifierToCharacterUseCase)
    factoryOf(::FlowSelectedModifiersForCharacterUseCase)
}
