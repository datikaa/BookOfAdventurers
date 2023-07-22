package com.datikaa.charlatan.launcher.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
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
import com.datikaa.charlatan.launcher.LauncherUiState
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
        openCharacter = openCharacter,
        openEditor = openEditor,
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
fun LauncherScreen(
    launcherUiState: LauncherUiState,
    openCharacter: (Int) -> Unit,
    openEditor: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        CmmTitledCard(
            title = "Characters",
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
                        Text(text = "Open Character editor")
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
        openCharacter = { },
        openEditor = { },
    )
}
