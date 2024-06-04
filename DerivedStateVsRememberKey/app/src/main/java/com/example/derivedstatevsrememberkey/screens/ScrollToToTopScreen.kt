package com.example.derivedstatevsrememberkey.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun ScrollToTopButton(
    state: LazyListState,
    isEnabled: Boolean = true
) {


    val scope = rememberCoroutineScope()

    /**
    this [derivedStateOf]  this will only updates the listeners to it only and not only this
    it will only recompose if and only if the last emitted  value is changed  not each emitted value changes
    but in some cases this also may need more and more recompostions like the [remember] with key
    approach also not allocating memory only if the last final emiited value is changed
     */

    /**
     * now we added [isEnabled] as a ky ? why ?
     * because if it's only enabled or only changed then move the logic to [derivedStateOf]
     */
    val showScrollToTopButton by remember(isEnabled) {
        derivedStateOf {
            state.firstVisibleItemIndex >= 5 && isEnabled
        }
    }

    /**
     * this will make a lot of not needed recompositions also it's a composable function
     * when passing a key so when this key is changed so it will recompose the whole compose tree
     * also it's a [Boolean] not a boolean state
     * also allocates memory at each recomposition
     */
    val showScrollToTopButtonUsingRemember = remember(state.firstVisibleItemIndex) {
        state.firstVisibleItemIndex >= 5

    }


    if (showScrollToTopButton) {
        FloatingActionButton(onClick = {
            scope.launch {
                state.animateScrollToItem(0)
            }
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
        }
    }


    /**
     * when to choose which ?
     * if the keys are changing more frequently use [derivedStateOf]
     * else you can choose [remember]with keys
     *
     */
}


@Composable
fun ItemsScreen() {
    val state = rememberLazyListState()
    Scaffold(
        floatingActionButton = {
            ScrollToTopButton(state)
        },
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding), state = state) {
            items(count = 50) { index ->
                Text(text = "item ${index + 1}", modifier = Modifier.padding(20.dp))
            }
        }
    }
}