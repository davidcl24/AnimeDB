package com.example.appejemplo.models

import com.google.gson.annotations.SerializedName

data class Animes(
    @SerializedName("data") val animes: List<Anime>
)
