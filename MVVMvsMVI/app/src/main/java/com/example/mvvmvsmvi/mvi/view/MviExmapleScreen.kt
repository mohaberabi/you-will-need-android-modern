package com.example.mvvmvsmvi.mvi.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.mvvmvsmvi.mvi.viewmodel.MviExampleState
import com.example.mvvmvsmvi.mvi.viewmodel.MviExmapleViewModel


@Composable

fun MviExampleRootScreen(
    viewModel: MviExmapleViewModel
) {

    MviExampleScreen(state = viewModel.state.collectAsState().value)

}


@Composable

fun MviExampleScreen(
    state: MviExampleState
) {

    if (state.isLoading) {
        CircularProgressIndicator()
    }


    Text(text = state.post?.id ?: "")
}