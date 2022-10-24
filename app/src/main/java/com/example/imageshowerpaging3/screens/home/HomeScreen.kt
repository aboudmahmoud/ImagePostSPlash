package com.example.imageshowerpaging3.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.imageshowerpaging3.nagavtion.Screen
import com.example.imageshowerpaging3.screens.comman.LisntContent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navHostController: NavHostController,
homeViewModel: HomeViewModel = hiltViewModel()
) {
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomaeAppBar(onSerachClicked = {
                navHostController.navigate(route = Screen.Serach.route)
            })
        },
        content = {
            Box(Modifier.padding(top = 75.dp)) {
                LisntContent(items = getAllImages)
            }
        }
    )
}