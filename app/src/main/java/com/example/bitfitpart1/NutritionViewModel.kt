package com.example.bitfitpart1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NutritionViewModel(private val repository: NutritionRepository) : ViewModel() {

    // Function to fetch all entries from the repository
    suspend fun getAllEntries(): LiveData<List<NutritionEntry>> {
        return repository.getAllEntries()
    }

    // Function to insert an entry using CoroutineScope
    fun insertEntry(entry: NutritionEntry) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertEntry(entry)
        }
    }
}