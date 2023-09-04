package com.bookofadventurers.feature.wizard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookofadventurers.feature.wizard.domain.GetListOfClassesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WizardViewModel(
    private val getListOfClassesUseCase: GetListOfClassesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val classes = getListOfClassesUseCase()
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

    fun toggleSkillForClass(classId: Long, modifierId: Int) = _uiState.update { uiState ->
        uiState.copy(
            selectableClasses = uiState.selectableClasses.map { classItem ->
                if (classItem.id == classId) {
                    var updatedSelectedSkills = classItem.selectableSkillProficiencyModifiers.map { skill ->
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
}

private val initialUiState: WizardUiState
    get() = WizardUiState(
        selectableClasses = emptyList(),
    )
