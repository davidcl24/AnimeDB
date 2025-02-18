package com.example.appejemplo.models

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("score") val score: Double,
    @SerializedName("rating") val rating: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("images") private val images: Map<String, Map<String, String>>
) {
    val imageUri: String get() = images["jpg"]?.get("image_url") ?: ""
}
