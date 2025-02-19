package com.example.appejemplo.models

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data") val anime: Anime // Ojo: "anime" en singular
)
