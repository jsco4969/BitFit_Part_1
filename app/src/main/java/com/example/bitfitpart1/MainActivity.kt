package com.example.bitfitpart1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfitp1.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NutritionViewModel
    private lateinit var adapter: NutritionEntryAdapter
    private lateinit var mealTypeEditText: EditText
    private lateinit var caloriesEditText: EditText
    private lateinit var addButton: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mealTypeEditText = findViewById(R.id.mealTypeEditText)
        caloriesEditText = findViewById(R.id.caloriesEditText)
        addButton = findViewById(R.id.addButton)
        recyclerView = findViewById(R.id.recyclerView)

        val dao = MyApp.database.nutritionEntryDao()
        val repository = NutritionRepository(dao)
        viewModel = ViewModelProvider(
            this,
            NutritionViewModelFactory(repository)
        )[NutritionViewModel::class.java]

        // Initialize RecyclerView
        adapter = NutritionEntryAdapter()
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            val mealType = mealTypeEditText.text.toString()
            val calories = caloriesEditText.text.toString().toInt()
            val entry = NutritionEntry(
                date = System.currentTimeMillis(),
                mealType = mealType,
                caloriesConsumed = calories
            )

            viewModel.insertEntry(entry)
        }
        lifecycleScope.launch {
            // Observe the LiveData within the activity's lifecycle
            viewModel.getAllEntries().observe(this@MainActivity) { entries ->
                // Update the UI with the new list of entries
                adapter.submitList(entries)
            }
        }
    }

}
