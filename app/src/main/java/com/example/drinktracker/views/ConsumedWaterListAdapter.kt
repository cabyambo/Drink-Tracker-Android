package com.example.drinktracker.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drinktracker.databinding.ConsumedWaterCellBinding
import com.example.drinktracker.models.Water

class ConsumedWaterListAdapter : RecyclerView.Adapter<ConsumedWaterListAdapter.ViewHolder>() {

    private val sampleData = listOf("Aquafina", "Dasani", "Zepheryhills", "Great Value", "Spring Water")
    private var water: Array<Water> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ConsumedWaterCellBinding = ConsumedWaterCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.consumedWaterNameTextView.text = water[position].companyName
    }

    override fun getItemCount(): Int {
        return water.size
    }

    @SuppressLint("NotifyDataSetChanged") // Entire data set is being changed
    fun updateWater(water: Array<Water>) {
        this.water = water
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: ConsumedWaterCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val consumedWaterNameTextView = binding.consumedWaterName
    }
}
