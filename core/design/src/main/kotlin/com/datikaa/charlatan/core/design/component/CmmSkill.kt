package com.datikaa.charlatan.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CmmSkill(
    name: String,
    score: Int,
    proficiency: Boolean,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        ) {
            Text(text = "$name:")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(start = 4.dp),
            ) {
                Text(text = "$score")
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .border(1.dp, LocalContentColor.current, CircleShape)
                        .run {
                            if (proficiency) background(
                                MaterialTheme.colorScheme.outline,
                                CircleShape
                            ) else this

                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun PrviewCmmSkill() {
    Column(Modifier.width(IntrinsicSize.Max)) {
        CmmSkill(name = "Skill name", score = 6, proficiency = true)
        CmmSkill(name = "Skill", score = 6, proficiency = false)
        CmmSkill(name = "Lonf Skill name", score = 6, proficiency = true)
    }
}
