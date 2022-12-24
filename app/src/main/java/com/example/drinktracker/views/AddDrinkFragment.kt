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

        setViewModelObservers()
        setClickListeners()
    }

    private fun setViewModelObservers() {
        // Water company names.
        // Only gets updated when the view models init method is called.
        viewModel.companyNamesLiveData.observe(this) { namesArray ->
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                namesArray
            ).also {
                binding.waterBottleBrandSpinner.adapter = it
                viewModel.getSelectedCompanyIndex()?.let { selected ->
                    binding.waterBottleBrandSpinner.setSelection(selected)
                }
            }
        }

        // Water company bottle sizes
        viewModel.bottleSizesLiveData.observe(this) { bottleSizes ->
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                bottleSizes
            ).also {
                binding.waterBottleSizeSpinner.adapter = it
                viewModel.getSelectedBottleSizeIndex()?.let { selected ->
                    binding.waterBottleSizeSpinner.setSelection(selected)
                }
            }
        }
    }

    private fun setClickListeners() {
        binding.waterBottleBrandSpinner.onItemSelectedListener = brandSpinnerClickListener
        binding.waterBottleSizeSpinner.onItemSelectedListener = bottleSizeSpinnerClickListener

        binding.addWaterButton.setOnClickListener {
            var water: Water?
            try {
                val companyName: String =
                    viewModel.companyNamesLiveData.value!![viewModel.getSelectedCompanyIndex()!!]
                val bottleSize: BottleSize =
                    viewModel.bottleSizesLiveData.value!![viewModel.getSelectedBottleSizeIndex()!!]
                water = Water(
                    companyName,
                    bottleSize
                )
            } catch (e: java.lang.NullPointerException) {
                throw java.lang.Exception(
                    "Encountered a null pointer while accessing the water entered by the user.",
                    e
                )
            }
            water.let {
                viewModel.addWaterToUser(water)
            }
            viewModel.setSelectedCompanyIndex(0)
            viewModel.setSelectedBottleSizeIndex(0)
            dismiss()
        }
    }

    private val brandSpinnerClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            viewModel.setSelectedCompanyIndex(p2)
            // Fetch the new Sizes that correspond to the new brand
            viewModel.fetchBottleSizes()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }
    }

    private val bottleSizeSpinnerClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            viewModel.setSelectedBottleSizeIndex(p2)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
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