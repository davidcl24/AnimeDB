package com.example.appejemplo.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("fav_anime")
data class Anime(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("score") val score: Double,
    @SerializedName("rating") val rating: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("synopsis") val synopsis: String,
    @SerializedName("images") val images: Map<String, Map<String, String>>
) {
    val imageUri: String get() = images["jpg"]?.get("image_url") ?: ""
}
