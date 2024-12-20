package com.datikaa.bookofadventurers.core.design.component

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
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(end = 4.dp),
            ) {
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
                Text(text = "$score")
            }
            Text(text = name)
        }
    }
}

@Preview
@Composable
fun PrviewCmmSkill() {
    Column(Modifier.width(IntrinsicSize.Max)) {
        CmmSkill(name = "Skill name", score = 6, proficiency = true)
        CmmSkill(name = "Skill", score = 6, proficiency = false)
        CmmSkill(name = "Long Skill name", score = 6, proficiency = true)
    }
}
