package com.example.imageshowerpaging3.nagavtion

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.imageshowerpaging3.screens.home.HomeScreen
import com.example.imageshowerpaging3.screens.search.SearchScreen

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetUpNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController =navHostController,
        startDestination=Screen.Home.route
    ){
composable(route = Screen.Home.route){

    HomeScreen(navHostController=navHostController)
}
        composable(route = Screen.Serach.route){
            SearchScreen(navHostController=navHostController)
        }
    }
}