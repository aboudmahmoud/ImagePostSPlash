package com.example.imageshowerpaging3.data.remote

import com.example.imageshowerpaging3.model.SerachResult
import com.example.imageshowerpaging3.model.UspalshImage
import retrofit2.http.GET

import retrofit2.http.Query


interface UnsplashApi {

   // @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImage(
        @Query("page")page:Int,
        @Query("per_page")perPage:Int
    ):List<UspalshImage>

  //  @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun SearchImage(
        @Query("query")query:String,
        @Query("per_page")perPage:Int
    ): SerachResult
}