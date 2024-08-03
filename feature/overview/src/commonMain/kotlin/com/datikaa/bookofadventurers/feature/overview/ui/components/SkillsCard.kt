package com.datikaa.bookofadventurers.feature.overview.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.datikaa.bookofadventurers.core.design.component.CmmSkill
import com.datikaa.bookofadventurers.core.design.component.CmmTitledCard
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import com.datikaa.bookofadventurers.feature.overview.ui.OverviewUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

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
            verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
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
