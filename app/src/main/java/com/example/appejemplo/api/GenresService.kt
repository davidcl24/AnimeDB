package com.example.appejemplo.api

import android.provider.MediaStore.Audio.Genres
import retrofit2.http.GET

interface GenresService {

    @GET("genres/anime")
    suspend fun getAllGenres() : Genres
}