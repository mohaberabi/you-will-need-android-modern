package com.example.persistlazycolumnposition

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce


@OptIn(FlowPreview::class)
@Composable
fun HomeScreen(
    lastSavedPosition: Int = 0,
    onSaveScrollPosition: (Int) -> Unit = {},
) {
    val state = rememberLazyListState(initialFirstVisibleItemIndex = lastSavedPosition)

    LaunchedEffect(state) {


        /**
         * this [snapshotFlow] converts a compose state into a kotlin flow
         * each time the compose state changed the flow emits a new value
         */
        snapshotFlow {
            state.firstVisibleItemIndex
        }.debounce(500L)
            .collectLatest { index ->
                onSaveScrollPosition(index)
            }
    }
    Scaffold(

    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding), state = state) {
            items(count = 50) { index ->
                Text(text = "item ${index + 1}", modifier = Modifier.padding(20.dp))
            }
        }
    }
}