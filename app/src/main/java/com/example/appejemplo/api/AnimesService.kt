package com.example.appejemplo.api

import com.example.appejemplo.models.Animes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimesService {
    @GET("anime")
    suspend fun getAllAnime() : Animes

    @GET("anime")
    suspend fun getAnimeByName(@Query("q") name: String) : Animes

    @GET("anime/{mal_id}")
    suspend fun getAnimeById(@Path("mal_id") id: Int) : Animes
}