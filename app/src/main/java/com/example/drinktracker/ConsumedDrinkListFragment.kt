package com.example.drinktracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drinktracker.databinding.FragmentConsumedDrinkListBinding

/**
 * A [Fragment] that displays a list of consumed drinks.
 */
class ConsumedDrinkListFragment : Fragment() {

    private var _binding: FragmentConsumedDrinkListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConsumedDrinkListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateDrinkList()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = ConsumedDrinkListAdapter()
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        requireContext().getDrawable(R.drawable.consumed_drink_item_decoration_background)
            ?.let { itemDecoration.setDrawable(it) }
        binding.consumedWaterRecyclerView.layoutManager = layoutManager
        binding.consumedWaterRecyclerView.adapter = adapter
        binding.consumedWaterRecyclerView.addItemDecoration(itemDecoration)
    }

    private fun populateDrinkList() {
        // Populate list with data stored in the shared preferences
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}