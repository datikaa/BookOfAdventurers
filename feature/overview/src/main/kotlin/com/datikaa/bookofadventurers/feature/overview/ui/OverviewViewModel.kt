package com.datikaa.bookofadventurers.feature.overview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.bookofadventurers.core.domain.calculateAbilityScore
import com.datikaa.bookofadventurers.core.domain.calculateSavingThrowScore
import com.datikaa.bookofadventurers.core.domain.calculateSkillCheckScore
import com.datikaa.bookofadventurers.core.domain.proficiencyScore
import com.datikaa.bookofadventurers.core.domain.proficientIn
import com.datikaa.bookofadventurers.feature.overview.domain.FlowCharacterUseCase
import com.datikaa.bookofadventurers.feature.overview.etc.name
import com.datikaa.bookofadventurers.feature.overview.etc.shortName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OverviewViewModel(
    characterId: Int,
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
                        className = char.boaClass.name,
                        proficiency = char.proficiencyScore,
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
                        },
                        savingThrows = char.savingThrow.map { savingThrow ->
                            OverviewUiState.UiSavingThrow(
                                name = savingThrow::class.simpleName ?: "",
                                baseName = char.abilityList.first { it::class == savingThrow.baseAbility }.shortName,
                                score = char.calculateSavingThrowScore(savingThrow),
                                proficiency = char.proficientIn(savingThrow),
                            )
                        }
                    )
                }
            }
        }
    }
}

private val initUiState = OverviewUiState(
    level = 1,
    name = "",
    className = "",
    proficiency = 0,
    abilities = emptyList(),
    skills = emptyList(),
    savingThrows = emptyList(),
)
