package com.example.imageshowerpaging3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imageshowerpaging3.utils.Constans.UNSPLASH_RMOTE_KEY_TABLE

@Entity(tableName = UNSPLASH_RMOTE_KEY_TABLE)
data class UsplashRomteKey(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val prevPage:Int?,
    val nextPage:Int?
)
