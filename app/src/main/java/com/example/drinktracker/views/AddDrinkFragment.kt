package com.example.drinktracker.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.drinktracker.R
import com.example.drinktracker.databinding.FragmentAddDrinkBinding
import com.example.drinktracker.models.BottleType
import com.example.drinktracker.models.Unit
import com.example.drinktracker.models.Water
import com.example.drinktracker.service.InternalStorageService

class AddDrinkFragment : DialogFragment() {

    private lateinit var internalStorageService: InternalStorageService
    private var _binding: FragmentAddDrinkBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddDrinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        internalStorageService = InternalStorageService(requireActivity().application)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.planets_array_long,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.waterBottleBrandSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.waterBottleSizeSpinner.adapter = adapter
        }

        binding.button2.setOnClickListener {
            Log.d("yest", "clicked")
            val water = Water(
                "Aquafina",
                BottleType(8f, Unit.OUNCE)
            )
            //internalStorageService.addWater(water)
            val allWater = internalStorageService.getAllWater()
            allWater.forEach {
                Log.d("yest", it.toString())
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface EventListener {
        fun onAddWaterClick(water: Water)
    }
}