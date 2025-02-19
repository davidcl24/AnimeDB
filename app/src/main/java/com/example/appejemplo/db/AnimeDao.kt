package com.example.appejemplo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appejemplo.models.Anime

@Dao
interface AnimeDao {
    @Insert
    suspend fun insertAnime(anime: Anime)

    @Update
    suspend fun updateAnime(anime: Anime)

    @Delete
    suspend fun deleteAnime(anime: Anime)

    @Query("SELECT * FROM fav_anime")
    fun getAllAnime(): LiveData<List<Anime>>

    @Query("SELECT * FROM fav_anime WHERE id = :id")
    fun getAnimeByID(id: Int): LiveData<Anime>

}