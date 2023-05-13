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
import com.datikaa.charlatan.feature.overview.domain.Attribute
import org.koin.androidx.compose.koinViewModel

@Composable
fun OverviewScreen(
    navigationEvent: (OverviewNavigation) -> Unit,
    modifier: Modifier = Modifier,
    overviewViewModel: OverviewViewModel = koinViewModel()
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    OverviewView(
        overviewUiState = uiState,
        clear = overviewViewModel::clearDb,
        navigateToAttributes = { navigationEvent(OverviewNavigation.Attributes) },
        navigateToCharacters = { navigationEvent(OverviewNavigation.Characters) },
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
fun OverviewView(
    overviewUiState: OverviewUiState,
    clear: () -> Unit,
    navigateToAttributes: () -> Unit,
    navigateToCharacters: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
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
                                Text(text = attr.name, fontSize = 10.sp, maxLines = 1)
                                Text(text = attr.value.toString(), fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
            CmmTitledCard(title = "Placeholder") {
                Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur commodo, lectus nec mollis tempus, tellus sapien ultrices nisl, commodo volutpat felis ipsum eget massa.")
            }
        }
        Row {
            Button(onClick = { navigateToAttributes() }) {
                Text("Attributes")
            }
            Button(onClick = { navigateToCharacters() }) {
                Text("Charcters")
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
    OverviewView(
        overviewUiState = OverviewUiState(
            attributes = listOf(
                Attribute("Str", 6),
                Attribute("Int", 5),
                Attribute("Dex", 4),
            )
        ),
        clear = {},
        navigateToAttributes = {},
        navigateToCharacters = {},
    )
}
