package com.example.imageshowerpaging3.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.imageshowerpaging3.screens.comman.LisntContent
import com.example.imageshowerpaging3.screens.home.HomeViewModel

@ExperimentalMaterial3Api
@ExperimentalPagingApi
@ExperimentalCoilApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navHostController: NavHostController,
                 serachViewModel: SearchViewModel = hiltViewModel()
) {
val searchQurey by serachViewModel.searchQurey
    val searchedImage=serachViewModel.searchedImage.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            SerachBar(
              text=searchQurey,
                onTextChange = {
                    Log.d("Aboud", "SwearchScreen: ${it}")
                    serachViewModel.udateSearchQure(it)
                },
                onCloseClicke = { navHostController.popBackStack()},
                onSerachCliced = {
                    Log.d("Aboud", "SearchScreen: ${it}")
                    serachViewModel.searchQured(it)
                },

            )
        },
        content = {
            LisntContent(searchedImage)
        }
    )

}