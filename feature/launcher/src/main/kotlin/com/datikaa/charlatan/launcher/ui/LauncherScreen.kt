package com.datikaa.charlatan.launcher.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.DevicePreviews
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LauncherRoute(
    openCharacter: (Int) -> Unit,
    openEditor: () -> Unit,
    modifier: Modifier = Modifier,
    launcherViewModel: LauncherViewModel = koinViewModel(),
) {
    val launcherUiState by launcherViewModel.uiState.collectAsStateWithLifecycle()

    LauncherScreen(
        launcherUiState = launcherUiState,
        clearDb = launcherViewModel::clearDb,
        openCharacter = openCharacter,
        openEditor = openEditor,
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LauncherScreen(
    launcherUiState: LauncherUiState,
    clearDb: () -> Unit,
    openCharacter: (Int) -> Unit,
    openEditor: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
        modifier = modifier,
    ) {
        CmmTitledCard(
            title = "Characters",
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                Row {
                    ElevatedButton(onClick = { openEditor() }) {
                        Text(text = "Manage characters")
                    }
                }
            }
        }
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = "Debug tools",
                    style = MaterialTheme.typography.bodySmall,
                )
                FlowRow {
                    OutlinedButton(onClick = { clearDb() }) {
                        Text("Clear DB")
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun LauncherScreenPreview() {
    LauncherScreen(
        launcherUiState = LauncherUiState(
            characters = listOf(
                LauncherUiState.CharacterListItem(id = 0, name = "Azmoday"),
                LauncherUiState.CharacterListItem(id = 1, name = "Barbarianna"),
                LauncherUiState.CharacterListItem(id = 2, name = "Saira"),
                LauncherUiState.CharacterListItem(id = 3, name = "Kosh"),
            ),
            modifiers = listOf()
        ),
        clearDb = { },
        openCharacter = { },
        openEditor = { },
    )
}
