package com.datikaa.bookofadventurers.launcher.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.bookofadventurers.core.analytics.LocalAnalyticsHelper
import com.datikaa.bookofadventurers.core.design.component.CmmTitledCard
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LauncherRoute(
    versionName: String,
    openBackgrounds: () -> Unit,
    openCharacter: (Int) -> Unit,
    openEditor: () -> Unit,
    openModifiers: () -> Unit,
    openWizard: () -> Unit,
    modifier: Modifier = Modifier,
    launcherViewModel: LauncherViewModel = koinViewModel(),
) {
    val launcherUiState by launcherViewModel.uiState.collectAsStateWithLifecycle()

    LauncherScreen(
        versionName = versionName,
        launcherUiState = launcherUiState,
        clearDb = launcherViewModel::clearDb,
        openBackgrounds = openBackgrounds,
        openCharacter = openCharacter,
        openEditor = openEditor,
        openModifiers = openModifiers,
        openWizard = openWizard,
        modifier = modifier,
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LauncherScreen(
    versionName: String,
    launcherUiState: LauncherUiState,
    clearDb: () -> Unit,
    openBackgrounds: () -> Unit,
    openCharacter: (Int) -> Unit,
    openEditor: () -> Unit,
    openModifiers: () -> Unit,
    openWizard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val analytics = LocalAnalyticsHelper.current
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Book of Adventurers") }) },
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
                title = "Characters",
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    launcherUiState.characters.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        ) {
                            Text(text = it.name)
                            TextButton(onClick = { openCharacter(it.id) }) {
                                Text(text = "OPEN SHEET")
                            }
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)
                    ) {
                        ElevatedButton(onClick = { openWizard() }) {
                            Text(text = "Create character")
                        }
                        ElevatedButton(onClick = { openEditor() }) {
                            Text(text = "Manage characters")
                        }
                    }
                }
            }
            CmmTitledCard(
                title = "Classes",
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    launcherUiState.classes.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        ) {
                            Text(text = it.name)
                        }
                    }
                }
            }

            CmmTitledCard(
                title = "Backgrounds",
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    launcherUiState.backgrounds.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        ) {
                            Text(text = it.name)
                        }
                    }
                    ElevatedButton(onClick = { openBackgrounds() }) {
                        Text(text = "Manage backgrounds")
                    }
                }
            }
            CmmTitledCard(
                title = "Modifiers",
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    launcherUiState.modifiers.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        ) {
                            Text(text = it.name)
                        }
                    }
                    Row {
                        ElevatedButton(onClick = { openModifiers() }) {
                            Text(text = "Manage modifiers")
                        }
                    }
                }
            }
            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(4.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "Debug tools",
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Text(
                            text = versionName,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    FlowRow {
                        OutlinedButton(onClick = { clearDb() }) {
                            Text("Clear DB")
                        }
                        OutlinedButton(onClick = {
                            try {
                                error("Debug non-fatal exception")
                            } catch (e: IllegalStateException) {
                                analytics.recordNonFatalException(e)
                            }
                        }) {
                            Text("Firebase Non-fatal")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LauncherScreenPreview() {
    LauncherScreen(
        versionName = "v0.0.1",
        launcherUiState = LauncherUiState(
            backgrounds = listOf(
                LauncherUiState.BackgroundListItem(id = 0, name = "Acolyte")
            ),
            characters = listOf(
                LauncherUiState.CharacterListItem(id = 0, name = "Azmoday"),
                LauncherUiState.CharacterListItem(id = 1, name = "Barbarianna"),
                LauncherUiState.CharacterListItem(id = 2, name = "Saira"),
                LauncherUiState.CharacterListItem(id = 3, name = "Kosh"),
            ),
            classes = emptyList(),
            modifiers = listOf(),
        ),
        clearDb = { },
        openBackgrounds = { },
        openCharacter = { },
        openEditor = { },
        openModifiers = { },
        openWizard = { },
    )
}
