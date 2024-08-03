package com.bookofadventurers.feature.background.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BackgroundsRoute(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundsViewModel: BackgroundsViewModel = koinViewModel(),
) {
    val backgroundsUiState by backgroundsViewModel.uiState.collectAsStateWithLifecycle()

    BackgroundsScreen(
        backgroundsUiState = backgroundsUiState,
        addNewBackground = backgroundsViewModel::addNewBackground,
        navigateBack = navigateBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackgroundsScreen(
    backgroundsUiState: BackgroundsUiState,
    addNewBackground: (String, String, String, String, List<Long>) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var addNewDialogVisible by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Backgrounds") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "backIcon")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { addNewDialogVisible = true }) {
                Icon(Icons.Rounded.Add, "add new background")
            }
        },
        modifier = modifier,
    ) { paddingValues ->
        var expandedBackgroundId: Long? by remember {
            mutableStateOf(null)
        }
        val lazyListState = rememberLazyListState()
        LaunchedEffect(expandedBackgroundId) {
            expandedBackgroundId?.also { id ->
                val index = backgroundsUiState.backgrounds.indexOfFirst { it.id == id }
                lazyListState.animateScrollToItem(index)
            }
        }
        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
            contentPadding = PaddingValues(all = BookOfAdventurersTheme.dimensions.screenPadding),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            if (backgroundsUiState.backgrounds.isEmpty()) {
                item {
                    Text(text = "No modifiers present")
                }
            } else {
                items(
                    items = backgroundsUiState.backgrounds,
                    key = { it.id },
                ) { background ->
                    BackgroundCard(
                        background = background,
                        expanded = expandedBackgroundId == background.id,
                        expand = { expandedBackgroundId = background.id },
                        collapse = { expandedBackgroundId = null },
                    )
                }
            }
        }
    }
    if (addNewDialogVisible) {
        AddNewBackgroundDialog(
            modifiers = backgroundsUiState.modifiers,
            addNewBackground = addNewBackground,
            onDismissRequest = { addNewDialogVisible = false }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BackgroundCard(
    background: BackgroundsUiState.Background,
    expanded: Boolean,
    expand: () -> Unit,
    collapse: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.animateContentSize()
    ) {
        Box(
            modifier = Modifier
                .clickable { if (expanded) collapse() else expand() }
                .fillMaxWidth()
                .run {
                    if (expanded) {
                        fillMaxSize()
                    } else this
                }
                .padding(all = 10.dp),
        ) {
            val rotation by animateFloatAsState(
                targetValue = if (expanded) 180f else 0f,
                label = "expand icon animation"
            )

            Icon(
                imageVector = Icons.Rounded.ExpandMore,
                contentDescription = "expand",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .rotate(rotation)
            )
            Column(verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                Text(
                    text = background.name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                Text(
                    text = "Feature: ${background.featureTitle}",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = background.featureDescription,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (expanded) Int.MAX_VALUE else 2,
                )
                Text(
                    text = "Suggested Characteristics",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = background.suggestedCharacteristics,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (expanded) Int.MAX_VALUE else 2,
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                FlowRow(horizontalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                    background.skillProficiencies.forEach {
                        ProficiencyItem(it)
                    }
                }
            }
        }
    }
}

@Composable
fun ProficiencyItem(
    proficiencyModifier: BackgroundsUiState.Modifier,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Box {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "check",
                tint = Color.Green,
                modifier = Modifier
                    .blur(3.dp)
                    .alpha(0.7f),
            )
            Icon(imageVector = Icons.Rounded.Check, contentDescription = "check")
        }
        Text(text = proficiencyModifier.name)
    }
}

@Preview
@Composable
private fun BackgroundsScreenPreview() {
    BackgroundsScreen(
        backgroundsUiState = BackgroundsUiState(
            backgrounds = listOf(
                BackgroundsUiState.Background(
                    id = 1,
                    name = "Background",
                    featureTitle = "Feature title",
                    featureDescription = "Feature desc",
                    suggestedCharacteristics = "Suggested charasteristics",
                    skillProficiencies = listOf(
                        BackgroundsUiState.Modifier(
                            id = 3481,
                            name = "Lester Santos",
                        )
                    )
                ),
            ),
            modifiers = emptyList(),
        ),
        addNewBackground = { _, _, _, _, _ -> },
        navigateBack = {},
    )
}
