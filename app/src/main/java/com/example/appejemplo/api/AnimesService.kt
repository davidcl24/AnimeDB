package com.example.appejemplo.api

import com.example.appejemplo.models.Animes
import retrofit2.http.GET

interface AnimesService {
    @GET("anime")
    suspend fun getAllAnime() : Animes
}