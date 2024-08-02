package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme

@Composable
fun WizardBackgroundComponent(
    backgroundItem: WizardUiState.BackgroundItem,
    selectBackground: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        border = if (backgroundItem.selected) BorderStroke(2.dp, Color.Green) else null,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
            modifier = Modifier
                .clickable {
                    selectBackground(backgroundItem.id)
                }
                .fillMaxWidth()
                .padding(all = 10.dp),
        ) {
            Text(
                text = backgroundItem.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Divider(modifier = Modifier.fillMaxWidth())
            Text(
                text = "Feature: ${backgroundItem.featureTitle}",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = backgroundItem.featureDescription,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
            Text(
                text = "Suggested Characteristics",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = backgroundItem.suggestedCharacteristics,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
            Divider(modifier = Modifier.fillMaxWidth())
            Row(horizontalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                backgroundItem.skillProficiencies.forEach {
                    ProficiencyItem(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun WizardBackgroundComponentPreview() {
    WizardBackgroundComponent(
        backgroundItem = WizardUiState.BackgroundItem(
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
        selectBackground = {},
    )
}