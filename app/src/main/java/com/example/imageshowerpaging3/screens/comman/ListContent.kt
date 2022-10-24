package com.example.imageshowerpaging3.screens.comman

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.imageshowerpaging3.R
import com.example.imageshowerpaging3.model.Urls
import com.example.imageshowerpaging3.model.User
import com.example.imageshowerpaging3.model.UserLink
import com.example.imageshowerpaging3.model.UspalshImage

@ExperimentalCoilApi
@Composable
fun LisntContent(items:LazyPagingItems<UspalshImage>) {
    Log.d("Aboud", "${items.loadState}")
    LazyColumn(modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(all=12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
       items(
            items = items,
           key = {it.id}
        ){
           it?.let { UnsplashItem(unsplashIamge=it) }
       }
    }
}
@ExperimentalCoilApi
@Composable
fun UnsplashItem(unsplashIamge:UspalshImage) {
    val painter = rememberImagePainter(data = unsplashIamge.urls.regular){
        crossfade(durationMillis = 1000)
        error(R.drawable.errorl)
        placeholder(R.drawable.image)
    }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                val browesrIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://unsplash.com/@${unsplashIamge.user.username}?utm_source=DemoApp&utm_medium=referral")
                )
                startActivity(context, browesrIntent, null)
            }
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "UnsplashImag",
            contentScale = ContentScale.Crop
        )
        Surface(modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .alpha(ContentAlpha.medium),
        color = Color.Black) {}
        Row(modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = buildAnnotatedString {
                append("Photo by ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                    append(unsplashIamge.user.username)
                }
                append(" on Unsplash")
            },
            color = Color.White,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
            LikeCounter(
                modifier = Modifier.weight(3f),
                painter= painterResource(id = R.drawable.redhart),
                likes = "${unsplashIamge.likes}"

            )
            
        }
    }
}

@Composable
fun LikeCounter(
    modifier: Modifier,
    painter: Painter,
    likes: String
) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painter,
            contentDescription = "Heart Icon",
            tint = Color.Unspecified
        )
        Divider(modifier = Modifier.width(6.dp))
        Text(
            text = likes,
            color = Color.White,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun UnsplashImagePreview() {
    UnsplashItem(
        unsplashIamge = UspalshImage(
            id = "1",
            urls = Urls(regular = ""),
            likes = 100,
            user = User(username = "Aboud", userLinks = UserLink(html = ""))
        )
    )
}
