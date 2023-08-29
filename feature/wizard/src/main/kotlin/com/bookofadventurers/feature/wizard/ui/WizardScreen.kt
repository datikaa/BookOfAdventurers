package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.datikaa.bookofadventurers.core.design.DevicePreviews
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun WizardRoute(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    overviewViewModel: WizardViewModel = koinViewModel()
) {

    WizardScreen(
        navigateBack = navigateBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WizardScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Character creator") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Rounded.ArrowBack, "backIcon")
                    }
                },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(state = scrollState)
                .padding(BookOfAdventurersTheme.dimensions.screenPadding),
        )
    }
}

@DevicePreviews
@Composable
private fun Preview() {
    WizardScreen(
        navigateBack = {},
    )
}
