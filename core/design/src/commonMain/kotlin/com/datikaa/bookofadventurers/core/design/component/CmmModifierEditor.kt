package com.datikaa.bookofadventurers.core.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CmmModifierEditor(
    text: String,
    decrease: () -> Unit,
    increase: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        shape = RoundedCornerShape(percent = 100),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier,
        ) {
            IconButton(onClick = decrease) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = "decrease"
                )
            }
            Text(text = text)
            IconButton(onClick = increase) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "increase"
                )
            }
        }
    }
}

@Preview
@Composable
private fun CmmModifierEditorPreview() {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max)
    ) {
        CmmModifierEditor(
            text = "Str",
            decrease = { -> },
            increase = { -> },
            modifier = Modifier.fillMaxWidth(),
        )
        CmmModifierEditor(
            text = "Strength",
            decrease = { -> },
            increase = { -> },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
