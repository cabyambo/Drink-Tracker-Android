package com.example.drinktracker.models

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.drinktracker.repository.WaterRepositoryInterface
import com.example.drinktracker.repository.WaterRepositoryLocalStorage
import com.example.drinktracker.service.InternalStorageService

class WaterViewModel(
    private val waterRepository: WaterRepositoryInterface
) : ViewModel() {
    private val water = MutableLiveData<Array<Water>>()
    val waterLiveDate: LiveData<Array<Water>>
        get() = water

    fun getAllWater() {
        // TODO: Get user ID
        // TODO: place in a coroutine
        water.value = waterRepository.getAllWaterForUser("id")
    }

    init {
        getAllWater()
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY] as Application)
                val internalStorageService: InternalStorageService =
                    InternalStorageService(application)
                val waterRepositoryLocalStorage: WaterRepositoryInterface =
                    WaterRepositoryLocalStorage(internalStorageService)
                WaterViewModel(waterRepositoryLocalStorage)
            }
        }
    }
}