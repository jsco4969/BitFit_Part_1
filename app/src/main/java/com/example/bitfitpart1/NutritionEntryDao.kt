package com.example.bitfitpart1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NutritionEntryDao {

    @Query("SELECT * FROM nutrition_entries")
    suspend fun getAllEntries(): List<NutritionEntry>

    @Insert
    suspend fun insertEntry(entry: NutritionEntry)
}