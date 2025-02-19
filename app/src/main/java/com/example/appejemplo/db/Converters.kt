package com.example.appejemplo.db

import androidx.room.TypeConverter
import com.example.appejemplo.models.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromGenresToJson(genres: List<Genre>): String =  Gson().toJson(genres)

    @TypeConverter
    fun toGenresFromJson(json: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun fromImagesToJson(images: Map<String, Map<String, String>>): String = Gson().toJson(images)
    @TypeConverter
    fun toImagesFromJson(json: String): Map<String, Map<String, String>> {
        val mapType = object : TypeToken<Map<String, Map<String, String>>>() {}.type
        return Gson().fromJson(json, mapType)
    }
}