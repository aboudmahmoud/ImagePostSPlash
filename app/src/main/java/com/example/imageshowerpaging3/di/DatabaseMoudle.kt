package com.example.imageshowerpaging3.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.imageshowerpaging3.utils.Constans.UNSPLAS_DATABASE
import com.example.imageshowerpaging3.data.local.UsplashILocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseMoudle {

    @Provides
    @Singleton
    fun ProvideDatabase(
        @ApplicationContext context: Context
    ): UsplashILocalDatabase{
        return Room.databaseBuilder(context,
            UsplashILocalDatabase::class.java,UNSPLAS_DATABASE).build()
    }
}