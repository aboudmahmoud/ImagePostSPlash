package com.example.imageshowerpaging3.di

import com.example.imageshowerpaging3.BuildConfig
import com.example.imageshowerpaging3.utils.Constans.BASE_URL
import com.example.imageshowerpaging3.data.remote.UnsplashApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object NetworkMoudle {

    @Provides
    @Singleton
    fun proivideOkHttpcline(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor { chain ->
                val builder: Request.Builder = chain.request().newBuilder()
                builder.addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}")
                //builder.addHeader("Content-Type", "multipart/form-data")
                val request: Request = builder.build()
                val response: okhttp3.Response = chain.proceed(request)
                response
            }
            .followRedirects(false).build()
    }


    @Singleton
    @Provides
    fun provideRetfit():Retrofit{
        val connectionType= MediaType.get("application/json")
        //We didnt Recive AllData So We Igonire it
        val json=Json{
            ignoreUnknownKeys=true
        }
        return Retrofit.Builder().client(
            proivideOkHttpcline()).baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(connectionType)).build()
    }
    @Singleton
    @Provides
    fun provideUsplashAPI(retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }
}