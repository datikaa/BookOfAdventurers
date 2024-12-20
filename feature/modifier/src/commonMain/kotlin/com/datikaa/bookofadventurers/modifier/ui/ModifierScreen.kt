package com.datikaa.bookofadventurers.modifier.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.bookofadventurers.core.design.component.CmmModifierEditor
import com.datikaa.bookofadventurers.core.design.component.CmmTitledCard
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.SavingThrow
import com.datikaa.bookofadventurers.core.domain.Skill
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.KClass
import com.datikaa.bookofadventurers.core.domain.Modifier as DomainModifier


@Composable
fun ModifierRoute(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    modifierViewModel: ModifierViewModel = koinViewModel(),
) {
    val modifierUiState by modifierViewModel.uiState.collectAsStateWithLifecycle()

    ModifierScreen(
        modifierUiState = modifierUiState,
        navigateBack = navigateBack,
        createNewModifier = modifierViewModel::createModifier,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifierScreen(
    modifierUiState: ModifierUiState,
    navigateBack: () -> Unit,
    createNewModifier: (AddNewModifier.Modifier) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Modifiers") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "backIcon")
                    }
                },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(state = scrollState)
                .padding(BookOfAdventurersTheme.dimensions.screenPadding),
        ) {
            CmmTitledCard(
                title = "Add new modifier",
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    var name by remember { mutableStateOf("") }
                    TextField(
                        label = { Text("Modifier name") },
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier.fillMaxWidth(),
                    )


                    var typeExpanded by remember { mutableStateOf(false) }
                    var selectedType by remember { mutableStateOf<AddNewModifier.Type?>(null) }

                    var scoreTypeExpanded by remember { mutableStateOf(false) }
                    var selectedScoreType by remember {
                        mutableStateOf<AddNewModifier.ScoreType?>(
                            null
                        )
                    }
                    val selectedScoreTypeEnabled by remember { derivedStateOf { selectedType != null } }

                    var abilityExpanded by remember { mutableStateOf(false) }
                    var selectedAbility by remember { mutableStateOf<AddNewModifier.Ability?>(null) }
                    val selectedAbilityEnabled by remember { derivedStateOf { selectedScoreType != null } }

                    ExposedDropdownMenuBox(
                        expanded = typeExpanded,
                        onExpandedChange = {
                            typeExpanded = !typeExpanded
                        }
                    ) {
                        TextField(
                            value = selectedType?.readableName ?: "Please select",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = typeExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = typeExpanded,
                            onDismissRequest = { typeExpanded = false }
                        ) {
                            AddNewModifier.Type.entries.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item.readableName) },
                                    onClick = {
                                        selectedAbility = null
                                        selectedScoreType = null
                                        selectedType = item
                                        typeExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    ExposedDropdownMenuBox(
                        expanded = scoreTypeExpanded,
                        onExpandedChange = {
                            scoreTypeExpanded = !scoreTypeExpanded
                        }
                    ) {
                        TextField(
                            value = selectedScoreType?.readableName ?: "Please select",
                            enabled = selectedScoreTypeEnabled,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = typeExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = scoreTypeExpanded,
                            onDismissRequest = { scoreTypeExpanded = false }
                        ) {
                            AddNewModifier.ScoreType.entries.filter {
                                selectedType == AddNewModifier.Type.Score || it != AddNewModifier.ScoreType.Ability
                            }.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item.readableName) },
                                    onClick = {
                                        selectedAbility = null
                                        selectedScoreType = item
                                        scoreTypeExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    val possibleAbilities by remember {
                        derivedStateOf<List<AddNewModifier.Ability>> {
                            when (selectedScoreType) {
                                AddNewModifier.ScoreType.Ability -> AddNewModifier.Ability.BaseAbility.entries
                                AddNewModifier.ScoreType.SavingThrow -> AddNewModifier.Ability.SavingThrow.entries
                                AddNewModifier.ScoreType.Skill -> AddNewModifier.Ability.Skill.entries
                                null -> emptyList()
                            }
                        }
                    }

                    ExposedDropdownMenuBox(
                        expanded = abilityExpanded,
                        onExpandedChange = {
                            abilityExpanded = !abilityExpanded
                        }
                    ) {
                        TextField(
                            value = selectedAbility?.readableName ?: "Please select",
                            enabled = selectedAbilityEnabled,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = typeExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = abilityExpanded,
                            onDismissRequest = { abilityExpanded = false }
                        ) {
                            possibleAbilities.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item.readableName) },
                                    onClick = {
                                        selectedAbility = item
                                        abilityExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    var value by remember { mutableStateOf(0) }
                    AnimatedVisibility(visible = selectedType == AddNewModifier.Type.Score) {
                        CmmModifierEditor(
                            text = "$value",
                            decrease = { value-- },
                            increase = { value++ },
                            modifier = Modifier.defaultMinSize(minWidth = 150.dp),
                        )
                    }

                    val buttonEnabled by remember {
                        derivedStateOf {
                            selectedType != null
                                    && selectedScoreType != null
                                    && selectedAbility != null
                                    && name.isNotBlank()
                        }
                    }

                    ElevatedButton(
                        enabled = buttonEnabled,
                        onClick = {
                            createNewModifier(
                                AddNewModifier.Modifier(
                                    type = selectedType!!,
                                    scoreType = selectedScoreType!!,
                                    ability = selectedAbility!!,
                                    name = name,
                                    value = value,
                                )
                            )
                            name = ""
                            selectedType = null
                            selectedScoreType = null
                            selectedAbility = null
                        },
                    ) {
                        Text(text = "Create Modifier")
                    }
                }
            }
            CmmTitledCard(
                title = "Modifiers",
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (modifierUiState.modifiers.isEmpty()) {
                    Text(text = "No modifiers present")
                } else {
                    Column {
                        modifierUiState.modifiers.forEach {
                            Text(text = it.toReadable())
                        }
                    }
                }
            }
        }
    }
}

private fun DomainModifier.toReadable() = when (this) {
    is DomainModifier.Holder -> "DomainModifier.Holder"
    is DomainModifier.Proficiency -> "$name: ${proficiencyType.toReadable("proficiency")}"
    is DomainModifier.Score -> "$name: ${modifiableScoreType.toReadable("score")}"
}

private fun KClass<*>.toReadable(type: String) = when (this) {
    Ability.Strength::class -> "Strength $type modifier"
    Ability.Dexterity::class -> "Dexterity $type modifier"
    Ability.Constitution::class -> "Constitution $type modifier"
    Ability.Intelligence::class -> "Intelligence $type modifier"
    Ability.Wisdom::class -> "Wisdom ability modifier"
    Ability.Charisma::class -> "Charisma ability modifier"
    Skill.Acrobatics::class -> "Acrobatics skill $type modifier"
    Skill.AnimalHandling::class -> "AnimalHandling skill $type modifier"
    Skill.Arcana::class -> "Arcana skill $type modifier"
    Skill.Athletics::class -> "Athletics skill $type modifier"
    Skill.Deception::class -> "Deception skill $type modifier"
    Skill.History::class -> "History skill $type modifier"
    Skill.Insight::class -> "Insight skill $type modifier"
    Skill.Intimidation::class -> "Intimidation skill $type modifier"
    Skill.Investigation::class -> "Investigation skill $type modifier"
    Skill.Medicine::class -> "Medicine skill $type modifier"
    Skill.Nature::class -> "Nature skill $type modifier"
    Skill.Perception::class -> "Perception skill $type modifier"
    Skill.Performance::class -> "Performance skill $type modifier"
    Skill.Persuasion::class -> "Persuasion skill $type modifier"
    Skill.Religion::class -> "Religion skill $type modifier"
    Skill.SleightOfHand::class -> "SleightOfHand skill $type modifier"
    Skill.Stealth::class -> "Stealth skill $type modifier"
    Skill.Survival::class -> "Survival skill $type modifier"
    SavingThrow.Strength::class -> "Strength saving throw $type modifier"
    SavingThrow.Dexterity::class -> "Dexterity saving throw $type modifier"
    SavingThrow.Constitution::class -> "Constitution saving throw $type modifier"
    SavingThrow.Intelligence::class -> "Intelligence saving throw $type modifier"
    SavingThrow.Wisdom::class -> "Wisdom saving throw $type modifier"
    SavingThrow.Charisma::class -> "Charisma saving throw $type modifier"

    else -> error("Unknown type: $this")
}

@Preview
@Composable
private fun PreviewModifierScreen() {
    ModifierScreen(
        modifierUiState = ModifierUiState(
            modifiers = listOf()
        ),
        navigateBack = {},
        createNewModifier = {}
    )
}
