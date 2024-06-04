package com.example.mvvmvsmvi.mvvm.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.mvvmvsmvi.model.Post
import com.example.mvvmvsmvi.mvvm.viewmodel.ViewModelExampleViewModel


@Composable

fun ViewModelScreenRootExample(
    viewModel: ViewModelExampleViewModel
) {

    ViewModelScreenExample(
        isLoading = viewModel.isLoading.collectAsState().value,
        post = viewModel.postDetail.collectAsState().value
    )
}


@Composable
fun ViewModelScreenExample(

    isLoading: Boolean,
    post: Post?
) {


    if (isLoading) {
        CircularProgressIndicator()
    } else {

        Text(text = post?.id ?: "")
    }
}