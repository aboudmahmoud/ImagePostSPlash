package com.example.imageshowerpaging3.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material3.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomaeAppBar(
    onSerachClicked:()->Unit
) {
TopAppBar(
  title = {
      Text(
          text="Home",
          color = MaterialTheme.colorScheme.primary)
  },
    colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor= MaterialTheme.colorScheme.secondaryContainer
    ),
    actions = {
        IconButton(onClick = onSerachClicked) {
        Icon(imageVector = Icons.Default.Search , contentDescription ="Search icon" )
        }
    }

)
}