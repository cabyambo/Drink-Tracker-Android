package com.example.drinktracker.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
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

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val internalStorageService: InternalStorageService =
                    InternalStorageService(application)
                val waterRepositoryLocalStorage: WaterRepositoryInterface =
                    WaterRepositoryLocalStorage(internalStorageService)
                return WaterViewModel(waterRepositoryLocalStorage) as T
            }
        }
    }
}