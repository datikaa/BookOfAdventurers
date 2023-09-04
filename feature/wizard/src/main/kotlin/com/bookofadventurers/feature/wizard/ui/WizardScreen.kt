package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
        toggleProficiency = wizardViewModel::toggleSkillForClass,
        openDialog = wizardViewModel::openDialog,
        dismissDialog = wizardViewModel::dismissDialog,
        modifier = modifier,
    )
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
)
@Composable
private fun WizardScreen(
    uiState: WizardUiState,
    navigateBack: () -> Unit,
    selectClass: (Long) -> Unit,
    toggleProficiency: (Long, Int) -> Unit,
    openDialog: (Long) -> Unit,
    dismissDialog: () -> Unit,
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
                    WizardClassComponent(
                        classItem = classItem,
                        selectClass = selectClass,
                        openProficiencyDialog = openDialog,
                        modifier = Modifier.alpha(
                            lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        )
                    )
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
    if (uiState.dialog != null) {
        WizardSkillSelectorDialog(
            dialogState = uiState.dialog,
            selectClass = uiState.selectableClasses.first { it.id == uiState.dialog.classId },
            onDismissRequest = dismissDialog,
            toggleProficiency = toggleProficiency,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    WizardScreen(
        uiState = WizardUiState(
            dialog = null,
            selectableClasses = listOf(
                WizardUiState.ClassItem(
                    id = 0,
                    name = "Barbarian",
                    selectableCount = 2,
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
                    selectableCount = 2,
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
                    selectableCount = 2,
                    savingThrowProficiencyModifiers = listOf(),
                    selectableSkillProficiencyModifiers = listOf(),
                    selected = false,
                )
            ),
        ),
        navigateBack = {},
        selectClass = {},
        openDialog = {},
        dismissDialog = {},
        toggleProficiency = { _, _ -> /* no-op */ },
    )
}
