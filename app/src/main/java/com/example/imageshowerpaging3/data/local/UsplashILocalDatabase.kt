package com.example.imageshowerpaging3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imageshowerpaging3.data.local.dao.UnsplashRemoteKeyDao
import com.example.imageshowerpaging3.data.local.dao.UsplashDao
import com.example.imageshowerpaging3.model.UspalshImage
import com.example.imageshowerpaging3.model.UsplashRomteKey

@Database(entities = [UspalshImage::class, UsplashRomteKey::class], version = 1)
abstract class UsplashILocalDatabase:RoomDatabase() {

    abstract fun unspalashImageDao(): UsplashDao
    abstract fun unspalashRemoteKeyDao(): UnsplashRemoteKeyDao
}