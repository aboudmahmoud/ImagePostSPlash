package com.example.imageshowerpaging3.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imageshowerpaging3.utils.Constans.UNSPLASH_IMAGE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UspalshImage(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    @Embedded
    val urls:Urls,
    val likes:Int,
    @Embedded
    val user:User,
)
