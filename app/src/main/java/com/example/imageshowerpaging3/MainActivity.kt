package com.example.imageshowerpaging3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.example.imageshowerpaging3.nagavtion.SetUpNavGraph
import com.example.imageshowerpaging3.ui.theme.ImageShowerPaging3Theme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPagingApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageShowerPaging3Theme {
               val navController= rememberNavController()
                SetUpNavGraph(navController)

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageShowerPaging3Theme {
        Greeting("Android")
    }
}