package com.example.mvvmvsmvi.mvvm.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmvsmvi.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelExampleViewModel(
    private val savedStateHandle: SavedStateHandle,

    ) : ViewModel() {


    private val _postDetails = MutableStateFlow<Post?>(null)
    val postDetail: StateFlow<Post?>
        get() = _postDetails


    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading


    private val _isLiked = MutableStateFlow<Boolean>(false)
    val isLiked: StateFlow<Boolean>
        get() = _isLiked

    init {
        savedStateHandle.get<String>("postId")?.let { postId ->

            loadPost(postId)
        }
    }


    fun toggleLike() {
        _isLiked.update {
            !it
        }
    }


    fun loadPost(id: String) {
        viewModelScope.launch {

            _isLoading.update {
                true
            }
            //get postdetails
            _isLoading.update {
                false
            }
        }
    }
}