package com.datikaa.charlatan.feature.attributes.di

import com.datikaa.charlatan.feature.attributes.domain.AddAttributeUseCase
import com.datikaa.charlatan.feature.attributes.domain.DecreaseValueOfAttributeUseCase
import com.datikaa.charlatan.feature.attributes.domain.FlowListOfCharacterAttributesUseCase
import com.datikaa.charlatan.feature.attributes.domain.FlowListOfCharactersUseCase
import com.datikaa.charlatan.feature.attributes.domain.IncreaseValueOfAttributeUseCase
import com.datikaa.charlatan.feature.attributes.ui.AttributesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val attributesKoinModule = module {
    viewModelOf(::AttributesViewModel)

    factoryOf(::AddAttributeUseCase)
    factoryOf(::DecreaseValueOfAttributeUseCase)
    factoryOf(::FlowListOfCharacterAttributesUseCase)
    factoryOf(::FlowListOfCharactersUseCase)
    factoryOf(::IncreaseValueOfAttributeUseCase)
}