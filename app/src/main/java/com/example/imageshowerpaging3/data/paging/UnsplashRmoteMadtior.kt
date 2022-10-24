package com.example.imageshowerpaging3.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.imageshowerpaging3.utils.Constans.ITEMS_PER_PAGE
import com.example.imageshowerpaging3.data.local.UsplashILocalDatabase
import com.example.imageshowerpaging3.data.local.dao.UnsplashRemoteKeyDao
import com.example.imageshowerpaging3.data.local.dao.UsplashDao
import com.example.imageshowerpaging3.data.remote.UnsplashApi
import com.example.imageshowerpaging3.model.UspalshImage
import com.example.imageshowerpaging3.model.UsplashRomteKey
import javax.inject.Inject

@ExperimentalPagingApi
class UnsplashRmoteMadtior(
    private val unsplashAPI: UnsplashApi,
    private val unspladDatabase: UsplashILocalDatabase,
):RemoteMediator<Int, UspalshImage>() {
    val  UnsplashImageDao: UsplashDao = unspladDatabase.unspalashImageDao()
    val  UnsplashRomteKeyDao:UnsplashRemoteKeyDao = unspladDatabase.unspalashRemoteKeyDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UspalshImage>
    ): MediatorResult {
//Here We Collatace the page
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = unsplashAPI.getAllImage(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            unspladDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    UnsplashImageDao.deletAllImage()
                    UnsplashRomteKeyDao.deleteAllKeys()
                }
                val keys = response.map { unsplashImage ->
                    UsplashRomteKey(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                UnsplashRomteKeyDao.addAllRemoteKeys(remoterKeys = keys)
                UnsplashImageDao.addImages(image = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UspalshImage>
    ): UsplashRomteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                UnsplashRomteKeyDao.getRmoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UspalshImage>
    ): UsplashRomteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                UnsplashRomteKeyDao.getRmoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UspalshImage>
    ): UsplashRomteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                UnsplashRomteKeyDao.getRmoteKeys(id = unsplashImage.id)
            }
    }
}