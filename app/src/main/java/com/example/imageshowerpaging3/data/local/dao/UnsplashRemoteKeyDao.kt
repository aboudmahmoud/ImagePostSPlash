package com.example.imageshowerpaging3.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imageshowerpaging3.utils.Constans.UNSPLASH_RMOTE_KEY_TABLE
import com.example.imageshowerpaging3.model.UsplashRomteKey

@Dao
interface UnsplashRemoteKeyDao {

    @Query("SELECT * FROM ${UNSPLASH_RMOTE_KEY_TABLE} WHERE id =:id")
    suspend fun getRmoteKeys(id:String):UsplashRomteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoterKeys:List<UsplashRomteKey>)

    @Query("DELETE  FROM ${UNSPLASH_RMOTE_KEY_TABLE}")
    suspend fun deleteAllKeys()

}