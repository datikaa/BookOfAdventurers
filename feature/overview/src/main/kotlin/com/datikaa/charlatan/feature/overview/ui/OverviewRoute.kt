package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.feature.overview.etc.shortName
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun OverviewRoute(
    characterId: Int,
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    overviewViewModel: OverviewViewModel = koinViewModel { parametersOf(characterId) }
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    OverviewScreen(
        overviewUiState = uiState,
        clear = overviewViewModel::clearDb,
        navigateToCharacter = { navigate("characters") },
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
fun OverviewScreen(
    overviewUiState: OverviewUiState,
    clear: () -> Unit,
    navigateToCharacter: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing)) {
            CmmTitledCard(title = "Attrs") {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    overviewUiState.attributes.forEach { attr ->
                        OutlinedCard(Modifier.width(IntrinsicSize.Min)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(all = 4.dp)
                                    .aspectRatio(1f, true)
                            ) {
                                Text(text = attr.shortName, fontSize = 10.sp, maxLines = 1)
                                Text(text = attr.value.toString(), fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
            CmmTitledCard(title = overviewUiState.name) {
                Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur commodo, lectus nec mollis tempus, tellus sapien ultrices nisl, commodo volutpat felis ipsum eget massa.")
            }
        }
        Row {
            Button(onClick = { navigateToCharacter() }) {
                Text("Characters")
            }
            Button(onClick = { clear() }) {
                Text("Clear DB")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OverviewScreen(
        overviewUiState = OverviewUiState(
            name = "Azmoday",
            attributes = listOf(
                Ability.Strength(6),
                Ability.Intelligence(5),
                Ability.Dexterity(4),
            )
        ),
        clear = {},
        navigateToCharacter = {},
    )
}
