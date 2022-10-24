package com.example.imageshowerpaging3.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerachResult(
    @SerialName("results")
    val images:List<UspalshImage>
)
