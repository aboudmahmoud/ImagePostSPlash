package com.example.imageshowerpaging3.screens.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.imageshowerpaging3.data.repostory.Repostrory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repsotry: Repostrory
) :ViewModel() {
    val getAllImages=repsotry.getAllImages()
}