package com.example.imageshowerpaging3.screens.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imageshowerpaging3.data.repostory.Repostrory
import com.example.imageshowerpaging3.model.UspalshImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(  private val  repsotry: Repostrory) :ViewModel(){
private val _searchQurey= mutableStateOf("")
     val searchQurey=_searchQurey
    private val _searcheadIagame= MutableStateFlow<PagingData<UspalshImage>>(PagingData.empty())
     val searchedImage=_searcheadIagame

    fun udateSearchQure(qurey:String){
        _searchQurey.value=qurey
    }
    fun searchQured(qurey:String){
        Log.d("Aboud", "searchQured: ${qurey}")
        viewModelScope.launch {
            repsotry.searachImages(qurey).cachedIn(viewModelScope).collect{
                _searcheadIagame.value=it
            }
        }
    }
}