package com.example.imageshowerpaging3.nagavtion

sealed class Screen(val route:String){
    object Home:Screen("home_screen")
    object Serach:Screen("serach_screen")
}

