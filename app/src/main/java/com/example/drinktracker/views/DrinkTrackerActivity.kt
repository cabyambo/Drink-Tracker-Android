package com.example.drinktracker.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.drinktracker.databinding.ActivityDrinkTrackerBinding
import com.example.drinktracker.models.Water

class DrinkTrackerActivity : AppCompatActivity(), AddDrinkFragment.EventListener {

    private lateinit var binding: ActivityDrinkTrackerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkTrackerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.floatingActionButton.setOnClickListener {
            showAddWaterFragment()
        }
    }

    private fun showAddWaterFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val currentDrinkFragment: Fragment? = supportFragmentManager.findFragmentByTag(
            ADD_WATER_FRAGMENT_TAG
        )
        currentDrinkFragment?.let {
            transaction.remove(it)
        }
        transaction.addToBackStack(null)

        val addDrinkFragment = AddDrinkFragment()
        addDrinkFragment.show(transaction, ADD_WATER_FRAGMENT_TAG)
    }

    companion object {
        const val ADD_WATER_FRAGMENT_TAG = "add_water_fragment_tag"
    }

    override fun onAddWaterClick(water: Water) {
        TODO("Not yet implemented")
    }
}