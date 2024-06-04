package com.example.hiltguide.presentation

import androidx.lifecycle.ViewModel
import com.example.hiltguide.data.LoserRepository1
import com.example.hiltguide.domain.LoserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel

class AppViewModel @Inject constructor(
    @Named("repo1") private val repo1: LoserRepository,
    @Named("repo2") private val repo2: LoserRepository
) : ViewModel() {


}