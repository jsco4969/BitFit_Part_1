package com.example.bitfitpart1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NutritionRepository(private val nutritionEntryDao: NutritionEntryDao) {

    suspend fun getAllEntries(): LiveData<List<NutritionEntry>> {
        val entriesLiveData = MutableLiveData<List<NutritionEntry>>()
        val entries = nutritionEntryDao.getAllEntries()
        entriesLiveData.value = entries
        return entriesLiveData
    }


    suspend fun insertEntry(entry: NutritionEntry) {
        withContext(Dispatchers.IO) {
            nutritionEntryDao.insertEntry(entry)
        }
    }
}