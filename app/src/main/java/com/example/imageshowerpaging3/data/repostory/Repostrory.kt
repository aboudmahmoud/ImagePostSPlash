package com.example.imageshowerpaging3.data.repostory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imageshowerpaging3.utils.Constans.ITEMS_PER_PAGE
import com.example.imageshowerpaging3.data.local.UsplashILocalDatabase
import com.example.imageshowerpaging3.data.paging.SearchPagingSource
import com.example.imageshowerpaging3.data.paging.UnsplashRmoteMadtior
import com.example.imageshowerpaging3.data.remote.UnsplashApi
import com.example.imageshowerpaging3.model.UspalshImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@ExperimentalPagingApi
class Repostrory @Inject constructor(
    private val unsplashAPI: UnsplashApi,
    private val unspladDatabase: UsplashILocalDatabase
) {

    fun getAllImages():Flow<PagingData<UspalshImage>>{
        val pagingSourceFactory={
            unspladDatabase.unspalashImageDao().getAllImages()
        }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRmoteMadtior(
                unsplashAPI = unsplashAPI,
                unspladDatabase=unspladDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searachImages(query:String):Flow<PagingData<UspalshImage>>{
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(
                    uspalshApi= unsplashAPI,
                    query = query
                )
            }
        ).flow
    }
}