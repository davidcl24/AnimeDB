package com.example.appejemplo.models

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("count") val count: Int
)
