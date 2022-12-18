package com.example.drinktracker.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drinktracker.databinding.ConsumedWaterCellBinding

class ConsumedDrinkListAdapter : RecyclerView.Adapter<ConsumedDrinkListAdapter.ViewHolder>() {

    private var _binding: ConsumedWaterCellBinding? = null
    private val binding get() = _binding!!

    val sampleData = listOf("Aquafina", "Dasani", "Zepheryhills", "Great Value", "Spring Water")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ConsumedWaterCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.consumedWaterNameTextView.text = sampleData[position]
    }

    override fun getItemCount(): Int {
        return sampleData.size
    }

    inner class ViewHolder(binding: ConsumedWaterCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val consumedWaterNameTextView = binding.consumedWaterName
    }
}
