package com.example.paging3.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.paging3.data.local.movie.MovieEntity
import com.example.paging3.data.mapper.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    pager :Pager<Int,MovieEntity>
) : ViewModel() {



    val movies = pager.flow.map {
        data->
        data.map { it.toMovie() }
    }.cachedIn(viewModelScope)
}