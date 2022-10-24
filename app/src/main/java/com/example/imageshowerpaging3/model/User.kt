package com.example.imageshowerpaging3.model

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User (
    @SerialName("links")
    @Embedded
    val userLinks:UserLink,
    val username:String

        )