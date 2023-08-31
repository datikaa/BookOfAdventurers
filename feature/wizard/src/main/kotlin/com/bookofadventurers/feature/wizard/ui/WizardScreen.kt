package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.bookofadventurers.core.design.DevicePreviews
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import org.koin.androidx.compose.koinViewModel
import kotlin.math.absoluteValue

@Composable
fun WizardRoute(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    wizardViewModel: WizardViewModel = koinViewModel()
) {
    val uiState by wizardViewModel.uiState.collectAsStateWithLifecycle()

    WizardScreen(
        uiState = uiState,
        navigateBack = navigateBack,
        selectClass = wizardViewModel::selectClass,
        selectProficiency = wizardViewModel::toggleSkillForClass,
        modifier = modifier,
    )
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)
@Composable
private fun WizardScreen(
    uiState: WizardUiState,
    navigateBack: () -> Unit,
    selectClass: (Long) -> Unit,
    selectProficiency: (Long, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Character creator") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Rounded.ArrowBack, "backIcon")
                    }
                },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Column(verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                var charNameText by remember { mutableStateOf("") }
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BookOfAdventurersTheme.dimensions.screenPadding),
                    value = charNameText,
                    onValueChange = { charNameText = it },
                    label = { Text("Name") })
                val pagerState = rememberPagerState(pageCount = { uiState.selectableClasses.size })
                HorizontalPager(
                    state = pagerState,
                    pageSpacing = 10.dp,
                    contentPadding = PaddingValues(horizontal = 40.dp),
                    verticalAlignment = Alignment.Top
                ) { page ->
                    val classItem = uiState.selectableClasses[page]
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    Card(
                        border = if (classItem.selected) BorderStroke(2.dp, Color.Green) else null,
                        modifier = Modifier
                            .clickable {
                                selectClass(classItem.id)
                            }
                            .alpha(
                                lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 10.dp),
                        ) {
                            Text(
                                text = classItem.name,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                            )
                            Divider(modifier = Modifier.fillMaxWidth())
                            Text(
                                text = "Proficiencies",
                                style = MaterialTheme.typography.headlineSmall,
                            )
                            Divider(modifier = Modifier.fillMaxWidth())
                            CompositionLocalProvider(
                                LocalMinimumInteractiveComponentEnforcement provides false
                            ) {
                                FlowRow(
                                    verticalArrangement = Arrangement.spacedBy(
                                        BookOfAdventurersTheme.dimensions.cardSpacing
                                    ),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        BookOfAdventurersTheme.dimensions.cardSpacing
                                    ),
                                ) {
                                    classItem.savingThrowProficiencyModifiers.forEach {
                                        FilterChip(
                                            selected = it.selected,
                                            onClick = { },
                                            label = { Text(text = it.name) },
                                            shape = CircleShape,
                                            trailingIcon = {
                                                Icon(
                                                    imageVector = if (it.selected) Icons.Rounded.Check else Icons.Rounded.Close,
                                                    contentDescription = "check"
                                                )
                                            },
                                        )
                                    }
                                }
                                Divider(modifier = Modifier.fillMaxWidth())
                                FlowRow(
                                    verticalArrangement = Arrangement.spacedBy(
                                        BookOfAdventurersTheme.dimensions.cardSpacing
                                    ),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        BookOfAdventurersTheme.dimensions.cardSpacing
                                    ),
                                ) {
                                    classItem.selectableSkillProficiencyModifiers.forEach {
                                        FilterChip(
                                            selected = it.selected,
                                            onClick = { selectProficiency(classItem.id, it.id) },
                                            label = { Text(text = it.name.split(" ")[0]) },
                                            shape = CircleShape,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Box(Modifier.weight(1f))
                Card(
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    ElevatedButton(
                        onClick = { },
                        modifier = Modifier
                            .padding(BookOfAdventurersTheme.dimensions.cardSpacing)
                            .fillMaxWidth()
                    ) {
                        Text("Create character")
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun Preview() {
    WizardScreen(
        uiState = WizardUiState(
            selectableClasses = listOf(
                WizardUiState.ClassItem(
                    id = 0,
                    name = "Barbarian",
                    savingThrowProficiencyModifiers = listOf(
                        WizardUiState.Modifier(
                            id = 0,
                            name = "dexterity",
                            selected = true,
                            selectable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "constitution",
                            selected = true,
                            selectable = false,
                        ),
                    ),
                    selectableSkillProficiencyModifiers = listOf(
                        WizardUiState.Modifier(
                            id = 0,
                            name = "dexterity",
                            selected = true,
                            selectable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "constitution",
                            selected = true,
                            selectable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "dexterity",
                            selected = false,
                            selectable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "constitution",
                            selected = false,
                            selectable = false,
                        ),
                    ),
                    selected = true,
                ),
                WizardUiState.ClassItem(
                    id = 0,
                    name = "Barbarian",
                    savingThrowProficiencyModifiers = listOf(
                        WizardUiState.Modifier(
                            id = 0,
                            name = "dexterity",
                            selected = true,
                            selectable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "constitution",
                            selected = true,
                            selectable = false,
                        ),
                    ),
                    selectableSkillProficiencyModifiers = listOf(),
                    selected = false,
                ),
                WizardUiState.ClassItem(
                    id = 0,
                    name = "Barbarian",
                    savingThrowProficiencyModifiers = listOf(),
                    selectableSkillProficiencyModifiers = listOf(),
                    selected = false,
                )
            ),
        ),
        navigateBack = {},
        selectClass = {},
        selectProficiency = { _, _ -> /* no-op */ },
    )
}
