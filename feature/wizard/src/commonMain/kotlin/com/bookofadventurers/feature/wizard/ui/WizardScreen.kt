package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.absoluteValue

@Composable
fun WizardRoute(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    wizardViewModel: WizardViewModel = koinViewModel()
) {
    val uiState by wizardViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.navigateBack) {
        navigateBack()
    }

    WizardScreen(
        uiState = uiState,
        navigateBack = navigateBack,
        selectClass = wizardViewModel::selectClass,
        selectBackground = wizardViewModel::selectBackground,
        toggleProficiency = wizardViewModel::toggleSkillForClass,
        openDialog = wizardViewModel::openDialog,
        dismissDialog = wizardViewModel::dismissDialog,
        createCharacter = wizardViewModel::createCharacter,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WizardScreen(
    uiState: WizardUiState,
    navigateBack: () -> Unit,
    selectClass: (Long) -> Unit,
    selectBackground: (Long) -> Unit,
    toggleProficiency: (Long, Int) -> Unit,
    openDialog: (Long) -> Unit,
    dismissDialog: () -> Unit,
    createCharacter: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Character creator") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "backIcon")
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
                    onValueChange = { charNameText = it.trimStart() },
                    label = { Text("Name") },
                    singleLine = true,
                )
                val scrollState = rememberScrollState()
                Column(
                    verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    ClassPager(
                        uiState = uiState,
                        selectClass = selectClass,
                        openDialog = openDialog,
                    )
                    BackgroundPager(
                        uiState = uiState,
                        selectBackground = selectBackground,
                    )
                }
                ElevatedButton(
                    enabled = charNameText.isNotBlank() && uiState.selectedClassCompleted && uiState.selectedBackgroundCompleted,
                    onClick = { createCharacter(charNameText.trim()) },
                    modifier = Modifier
                        .padding(BookOfAdventurersTheme.dimensions.cardSpacing)
                        .fillMaxWidth()
                ) {
                    Text("Create character")
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClassPager(
    uiState: WizardUiState,
    selectClass: (Long) -> Unit,
    openDialog: (Long) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { uiState.selectableClasses.size })
    HorizontalPager(
        state = pagerState,
        pageSpacing = 10.dp,
        contentPadding = PaddingValues(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BackgroundPager(
    uiState: WizardUiState,
    selectBackground: (Long) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { uiState.selectableBackgrounds.size })
    HorizontalPager(
        state = pagerState,
        pageSpacing = 10.dp,
        contentPadding = PaddingValues(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
    ) { page ->
        val backgroundItem = uiState.selectableBackgrounds[page]
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
        WizardBackgroundComponent(
            backgroundItem = backgroundItem,
            selectBackground = selectBackground,
            modifier = Modifier.alpha(
                lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            )
        )
    }
}

private val WizardUiState.selectedClassCompleted: Boolean
    get() = selectableClasses.firstOrNull { it.selected }?.run {
        selectableSkillProficiencyModifiers.count { it.selected } == selectableCount
    } ?: false

private val WizardUiState.selectedBackgroundCompleted: Boolean
    get() = selectableBackgrounds.any { it.selected }

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
                            editable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "constitution",
                            selected = true,
                            editable = false,
                        ),
                    ),
                    selectableSkillProficiencyModifiers = listOf(
                        WizardUiState.Modifier(
                            id = 0,
                            name = "dexterity",
                            selected = true,
                            editable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "constitution",
                            selected = true,
                            editable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "dexterity",
                            selected = false,
                            editable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "constitution",
                            selected = false,
                            editable = false,
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
                            editable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "constitution",
                            selected = true,
                            editable = false,
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
            selectableBackgrounds = listOf(
                WizardUiState.BackgroundItem(
                    id = 4946,
                    name = "Acolyte",
                    featureTitle = "Shelter of the Faithful",
                    featureDescription = "As an acolyte, you command the respect of those who share your faith, and you can perform the religious ceremonies of your deity. You and your adventuring companions can expect to receive free healing and care at a temple, shrine, or other established presence of your faith, though you must provide any material components needed for spells. Those who share your religion will support you (but only you) at a modest lifestyle.\n" +
                            "\n" +
                            "You might also have ties to a specific temple dedicated to your chosen deity or pantheon, and you have a residence there. This could be the temple where you used to serve, if you remain on good terms with it, or a temple where you have found a new home. While near your temple, you can call upon the priests for assistance, provided the assistance you ask for is not hazardous and you remain in good standing with your temple.",
                    suggestedCharacteristics = "Acolytes are shaped by their experience in temples or other religious communities. Their study of the history and tenets of their faith and their relationships to temples, shrines, or hierarchies affect their mannerisms and ideals. Their flaws might be some hidden hypocrisy or heretical idea, or an ideal or bond taken to an extreme.",
                    skillProficiencies = listOf(
                        WizardUiState.Modifier(
                            id = 0,
                            name = "Insight",
                            selected = true,
                            editable = false,
                        ),
                        WizardUiState.Modifier(
                            id = 0,
                            name = "Religion",
                            selected = true,
                            editable = false,
                        ),
                    ),
                    selected = true,
                ),
            ),
            navigateBack = false,
        ),
        navigateBack = {},
        selectBackground = {},
        selectClass = {},
        openDialog = {},
        dismissDialog = {},
        createCharacter = {},
        toggleProficiency = { _, _ -> /* no-op */ },
    )
}
