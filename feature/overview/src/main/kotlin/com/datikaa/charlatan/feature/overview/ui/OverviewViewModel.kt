package com.datikaa.charlatan.feature.overview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.core.domain.calculateAbilityScore
import com.datikaa.charlatan.core.domain.calculateSkillCheckScore
import com.datikaa.charlatan.core.domain.proficientIn
import com.datikaa.charlatan.feature.overview.domain.ClearEverythingUseCase
import com.datikaa.charlatan.feature.overview.domain.FlowCharacterUseCase
import com.datikaa.charlatan.feature.overview.etc.name
import com.datikaa.charlatan.feature.overview.etc.shortName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OverviewViewModel(
    characterId: Int,
    private val clearEverythingUseCase: ClearEverythingUseCase,
    private val flowCharacterUseCase: FlowCharacterUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initUiState)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            flowCharacterUseCase(characterId = characterId).collectLatest { char ->
                _uiState.update { uiState ->
                    uiState.copy(
                        level = char.level,
                        name = char.name,
                        abilities = char.abilityList.map {
                            OverviewUiState.UiAbility(
                                name = it.name,
                                shortName = it.shortName,
                                baseScore = it.value,
                                calculatedScore = char.calculateAbilityScore(it::class),
                            )
                        },
                        skills = char.skills.map { skill ->
                            OverviewUiState.UiSkill(
                                name = skill::class.simpleName ?: "",
                                baseName = char.abilityList.first { it::class == skill.baseAbility }.shortName,
                                score = char.calculateSkillCheckScore(skill),
                                proficiency = char.proficientIn(skill),
                            )
                        }
                    )
                }
            }
        }
    }

    fun clearDb() {
        viewModelScope.launch {
            clearEverythingUseCase()
        }
    }
}

private val initUiState = OverviewUiState(
    level = 1,
    name = "",
    abilities = emptyList(),
    skills = emptyList(),
)
