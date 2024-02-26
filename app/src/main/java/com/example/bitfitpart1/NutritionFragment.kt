package com.example.bitfitpart1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class NutritionFragment : Fragment() {

    private lateinit var viewModel: NutritionViewModel
    private lateinit var adapter: NutritionEntryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_nutrition, container, false)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[NutritionViewModel::class.java]

        // Initialize RecyclerView and Adapter
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        adapter = NutritionEntryAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe LiveData from ViewModel
        // Launching a coroutine scope
        lifecycleScope.launch {
            viewModel.getAllEntries().observe(viewLifecycleOwner) { entries ->
                // Update the UI with the new list of entries
                adapter.submitList(entries)
            }
        }



        return rootView
    }
}