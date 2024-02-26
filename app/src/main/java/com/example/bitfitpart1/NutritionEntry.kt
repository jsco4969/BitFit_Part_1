package com.example.bitfitpart1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition_entries")
data class NutritionEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long, // timestamp
    val mealType: String,
    val caloriesConsumed: Int
)