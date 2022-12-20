package com.example.drinktracker.views

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.drinktracker.databinding.FragmentAddDrinkBinding
import com.example.drinktracker.models.*
import com.example.drinktracker.models.Unit
import kotlin.Int
import kotlin.Long
import kotlin.TODO
import kotlin.also


class AddDrinkFragment : DialogFragment() {

    private var _binding: FragmentAddDrinkBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WaterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddDrinkBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            requireActivity(),
            WaterViewModel.Factory
        ).get(WaterViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Spinner adapters
        binding.waterBottleBrandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setSelectedWaterIndex(p2)
                viewModel.fetchBottleTypes(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        // observe the view model
        setViewModelObservers()

        binding.addWaterButton.setOnClickListener {
            Log.d("yest", "clicked")
            val water = Water(
                "Aquafina",
                BottleType(8f, Unit.OUNCE)
            )
        }
    }

    private fun setViewModelObservers() {

        // Water company names
        viewModel.waterCompanyNamesLiveData.observe(this) { namesArray ->
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                namesArray
            ).also {
                binding.waterBottleBrandSpinner.adapter = it
                viewModel.getSelectedWaterBrandIndex()?.let { selectedItemIndex ->
                    binding.waterBottleBrandSpinner.setSelection(selectedItemIndex)
                }
            }
        }

        // Water company bottle sizes
        viewModel.bottleTypesLiveData.observe(this) { bottleTypes ->
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                bottleTypes
            ).also {
                binding.waterBottleSizeSpinner.adapter = it
//                viewModel.getSelectedWaterBrandIndex()?.let { selectedItemIndex ->
//                    binding.waterBottleBrandSpinner.setSelection(selectedItemIndex)
//                }
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