package com.datikaa.charlatan.feature.attributes.ui

import com.datikaa.charlatan.feature.attributes.domain.Attribute
import com.datikaa.charlatan.feature.attributes.domain.Character

data class AttributesUiState(
    val attributeList: List<Attribute>,
    val characters: List<Character>,
    val selectedCharacter: Character?
)
