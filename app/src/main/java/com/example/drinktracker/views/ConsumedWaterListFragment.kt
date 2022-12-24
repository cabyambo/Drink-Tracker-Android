package com.example.drinktracker.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinktracker.R
import com.example.drinktracker.databinding.FragmentConsumedWaterListBinding
import com.example.drinktracker.models.WaterViewModel

/**
 * A [Fragment] that displays a list of consumed drinks.
 */
class ConsumedWaterListFragment : Fragment() {

    private var _binding: FragmentConsumedWaterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WaterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConsumedWaterListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        viewModel = ViewModelProvider(
            requireActivity(),
            WaterViewModel.Factory
        ).get(WaterViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
    }

    private fun setupRecyclerView() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = ConsumedWaterListAdapter()
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.consumed_drink_item_decoration_background
        )?.let {
            itemDecoration.setDrawable(it)
        }
        binding.consumedWaterRecyclerView.layoutManager = layoutManager
        binding.consumedWaterRecyclerView.adapter = adapter
        binding.consumedWaterRecyclerView.addItemDecoration(itemDecoration)
    }

    private fun setViewModelObservers() {
        viewModel.waterLiveData.observe(viewLifecycleOwner) { water ->
            val adapter: ConsumedWaterListAdapter =
                binding.consumedWaterRecyclerView.adapter as ConsumedWaterListAdapter
            adapter.updateWater(water)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}