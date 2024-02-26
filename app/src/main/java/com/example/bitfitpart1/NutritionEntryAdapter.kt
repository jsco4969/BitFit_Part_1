package com.example.bitfitpart1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfitp1.R
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class NutritionEntryAdapter : ListAdapter<NutritionEntry, NutritionEntryAdapter.NutritionEntryViewHolder>(NutritionEntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionEntryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_nutrition_entry, parent, false)
        return NutritionEntryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NutritionEntryViewHolder, position: Int) {
        val entry = getItem(position)
        holder.bind(entry)
    }

    inner class NutritionEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val mealTypeTextView: TextView = itemView.findViewById(R.id.mealTypeTextView)
        private val caloriesTextView: TextView = itemView.findViewById(R.id.caloriesTextView)

        fun bind(entry: NutritionEntry) {
            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
            dateTextView.text = dateFormat.format(Date(entry.date))
            mealTypeTextView.text = entry.mealType
            caloriesTextView.text = entry.caloriesConsumed.toString()
        }
    }
}

class NutritionEntryDiffCallback : DiffUtil.ItemCallback<NutritionEntry>() {
    override fun areItemsTheSame(oldItem: NutritionEntry, newItem: NutritionEntry): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NutritionEntry, newItem: NutritionEntry): Boolean {
        return oldItem == newItem
    }
}
