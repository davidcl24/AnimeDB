package com.example.appejemplo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appejemplo.models.Anime

@Database(entities = [Anime::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DatabaseAnime : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}