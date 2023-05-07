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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.feature.overview.domain.Attribute
import org.koin.androidx.compose.koinViewModel

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier, overviewViewModel: OverviewViewModel = koinViewModel()
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    OverviewView(
        overviewUiState = uiState,
        addAttrName = overviewViewModel::addAttribute,
        addCharName = overviewViewModel::addChar,
        clear = overviewViewModel::clearDb,
        modifier = modifier,
    )
}

@Composable
fun OverviewView(
    overviewUiState: OverviewUiState,
    addCharName: (String) -> Unit,
    addAttrName: (String) -> Unit,
    clear: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.then(Modifier.padding(4.dp)),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            CmmTitledCard(title = "Attributes") {
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
                                Text(text = attr.name, fontSize = 10.sp)
                                Text(text = attr.value.toString(), fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
            CmmTitledCard(title = "Add stuff here") {
                Column {
                    var charNameText by remember { mutableStateOf("") }
                    var attrNameText by remember { mutableStateOf("") }
                    val addName = {
                        addCharName(charNameText)
                        charNameText = ""
                    }
                    val addAttr = {
                        addAttrName(attrNameText)
                        attrNameText = ""
                    }
                    OutlinedTextField(
                        value = charNameText,
                        onValueChange = { charNameText = it },
                        label = { Text("charName") }
                    )
                    Button(onClick = addName) {
                        Text("Add")
                    }
                    OutlinedTextField(
                        value = attrNameText,
                        onValueChange = { attrNameText = it },
                        label = { Text("attrName") }
                    )
                    Button(onClick = addAttr) {
                        Text("Add")
                    }
                }
            }
        }
        Button(onClick = { clear() }) {
            Text("Clear DB")
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
        addCharName = {},
        addAttrName = {},
        clear = {},
    )
}
