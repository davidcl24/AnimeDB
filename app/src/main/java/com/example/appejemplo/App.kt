package com.example.appejemplo

import android.app.Application
import androidx.room.Room
import com.example.appejemplo.db.DatabaseAnime

class App : Application() {
    companion object {
        lateinit var db: DatabaseAnime
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this,
            DatabaseAnime::class.java,
            "anime-db").build()
    }
}