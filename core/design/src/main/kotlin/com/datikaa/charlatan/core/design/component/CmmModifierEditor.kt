package com.datikaa.charlatan.core.design.component

import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CmmModifierEditor(
    text: String,
    decrease: () -> Unit,
    increase: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        shape = RoundedCornerShape(percent = 100),
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
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
    CmmModifierEditor(
        text = "Strength",
        decrease = { -> },
        increase = { -> },
    )
}
