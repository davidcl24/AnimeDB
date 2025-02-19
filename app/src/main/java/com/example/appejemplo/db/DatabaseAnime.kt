package com.example.appejemplo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appejemplo.models.Anime

@Database(entities = [Anime::class], version = 1)
abstract class DatabaseAnime : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}