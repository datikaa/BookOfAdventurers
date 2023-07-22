package com.datikaa.charlatan.feature.overview.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.datikaa.charlatan.core.design.component.CmmSkill
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.feature.overview.ui.OverviewUiState

@Composable
internal fun SkillsCard(
    skills: List<OverviewUiState.UiSkill>,
    modifier: Modifier = Modifier,
) {
    CmmTitledCard(
        title = "Skills",
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
            modifier = Modifier.width(IntrinsicSize.Max),
        ) {
            skills.forEach { skill ->
                CmmSkill(
                    name = "${skill.name} (${skill.baseName})",
                    score = skill.score,
                    proficiency = skill.proficiency,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSkillsCard() {
    SkillsCard(
        skills =
        listOf(
            OverviewUiState.UiSkill(
                name = "Strength", baseName = "Str", score = 12, proficiency = false,
            ),
            OverviewUiState.UiSkill(
                name = "Slight of Hand", baseName = "Cha", score = 12, proficiency = true,
            ),
        )
    )
}
