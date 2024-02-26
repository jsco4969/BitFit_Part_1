package com.example.bitfitpart1

import android.app.Application
import androidx.room.Room

class MyApp : Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize the database
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "nutrition_database"
        ).build()
    }
}