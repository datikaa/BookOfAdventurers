package com.bookofadventurers.feature.wizard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookofadventurers.feature.wizard.domain.AddCharacterUseCase
import com.bookofadventurers.feature.wizard.domain.GetListOfBackgroundsUseCase
import com.bookofadventurers.feature.wizard.domain.GetListOfClassesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WizardViewModel(
    private val addCharacterUseCase: AddCharacterUseCase,
    private val getListOfClassesUseCase: GetListOfClassesUseCase,
    private val getListOfBackgroundsUseCase: GetListOfBackgroundsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val classes = getListOfClassesUseCase()
            val backgrounds = getListOfBackgroundsUseCase()
            _uiState.update { uiState ->
                uiState.copy(
                    selectableClasses = classes.map { domainClass ->
                        WizardUiState.ClassItem(
                            id = domainClass.id,
                            name = domainClass.name,
                            selectableCount = domainClass.selectableSkillCount,
                            savingThrowProficiencyModifiers = domainClass.savingThrowProficiencies.map { domainSavingThrows ->
                                WizardUiState.Modifier(
                                    id = domainSavingThrows.id,
                                    name = domainSavingThrows.name,
                                    selected = true,
                                    editable = false,
                                )
                            },
                            selectableSkillProficiencyModifiers = domainClass.selectableSkillProficiencies.map { domainSkills ->
                                WizardUiState.Modifier(
                                    id = domainSkills.id,
                                    name = domainSkills.name,
                                    selected = false,
                                    editable = true,
                                )
                            },
                            selected = false,
                        )
                    },
                    selectableBackgrounds = backgrounds.map { background ->
                        WizardUiState.BackgroundItem(
                            id = background.id,
                            name = background.name,
                            featureTitle = background.featureTitle,
                            featureDescription = background.featureDescription,
                            suggestedCharacteristics = background.suggestedCharacteristics,
                            skillProficiencies = background.skillProficiencies.map { domainSkills ->
                                WizardUiState.Modifier(
                                    id = domainSkills.id,
                                    name = domainSkills.name,
                                    selected = true,
                                    editable = false,
                                )
                            },
                            selected = false
                        )
                    }
                )
            }
        }
    }

    fun selectClass(selectedClassId: Long) = _uiState.update { uiState ->
        uiState.copy(
            selectableClasses = uiState.selectableClasses.map { classItem ->
                classItem.copy(selected = classItem.id == selectedClassId)
            }
        )
    }

    fun selectBackground(selectedBackgroundId: Long) = _uiState.update { uiState ->
        uiState.copy(
            selectableBackgrounds = uiState.selectableBackgrounds.map { backgroundItem ->
                backgroundItem.copy(selected = backgroundItem.id == selectedBackgroundId)
            }
        )
    }

    fun toggleSkillForClass(classId: Long, modifierId: Int) = _uiState.update { uiState ->
        uiState.copy(
            selectableClasses = uiState.selectableClasses.map { classItem ->
                if (classItem.id == classId) {
                    var updatedSelectedSkills =
                        classItem.selectableSkillProficiencyModifiers.map { skill ->
                            if (skill.id == modifierId) {
                                skill.copy(
                                    selected = !skill.selected
                                )
                            } else skill
                        }
                    updatedSelectedSkills = updatedSelectedSkills.map { skill ->
                        skill.copy(
                            editable = skill.selected || updatedSelectedSkills.count { it.selected } < classItem.selectableCount
                        )
                    }
                    classItem.copy(
                        selectableSkillProficiencyModifiers = updatedSelectedSkills,
                    )
                } else classItem
            }
        )
    }

    fun openDialog(classId: Long) = _uiState.update { uiState ->
        val classToOpenFor = uiState.selectableClasses.first { it.id == classId }
        uiState.copy(
            dialog = WizardUiState.Dialog(
                classId = classId,
                selectableCount = classToOpenFor.selectableCount,
            )
        )
    }

    fun dismissDialog() = _uiState.update { uiState ->
        uiState.copy(dialog = null)
    }

    fun createCharacter(name: String) {
        viewModelScope.launch {
            val selectedBackground = uiState.value.selectableBackgrounds.first { it.selected }
            val selectedClass = uiState.value.selectableClasses.first { it.selected }
            addCharacterUseCase(
                name,
                selectedBackground.id,
                selectedClass.id,
                selectedClass.selectableSkillProficiencyModifiers.filter { it.selected }
                    .map { it.id })
            _uiState.update { uiState ->
                uiState.copy(
                    navigateBack = true
                )
            }
        }
    }
}

private val initialUiState: WizardUiState
    get() = WizardUiState(
        dialog = null,
        selectableClasses = emptyList(),
        selectableBackgrounds = emptyList(),
        navigateBack = false
    )
