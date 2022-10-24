package com.example.imageshowerpaging3.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imageshowerpaging3.utils.Constans.UNSPLASH_IMAGE_TABLE
import com.example.imageshowerpaging3.model.UspalshImage

@Dao
interface UsplashDao {

    @Query("Select * From ${UNSPLASH_IMAGE_TABLE}")
    fun getAllImages():PagingSource<Int,UspalshImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(image:List<UspalshImage>)

    @Query("Delete From ${UNSPLASH_IMAGE_TABLE}")
    suspend fun deletAllImage()
}