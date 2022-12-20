package com.example.drinktracker.models

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.drinktracker.repository.definitions.WaterCompanyRepositoryInterface
import com.example.drinktracker.repository.definitions.WaterConsumptionRepositoryInterface
import com.example.drinktracker.repository.localstorage.WaterCompanyRepositoryLocalStorage
import com.example.drinktracker.repository.localstorage.WaterConsumptionRepositoryLocalStorage
import com.example.drinktracker.service.localstorage.InternalCompanyStorageService
import com.example.drinktracker.service.localstorage.InternalConsumptionStorageService

class WaterViewModel(
    private val waterConsumptionRepository: WaterConsumptionRepositoryInterface,
    private val waterCompanyRepository: WaterCompanyRepositoryInterface
) : ViewModel() {
    private val water = MutableLiveData<Array<Water>>()
    private val waterCompanyNames = MutableLiveData<Array<String>>()
    private val bottleTypes = MutableLiveData<Array<BottleType>>()

    val waterLiveDate: LiveData<Array<Water>>
        get() = water
    val waterCompanyNamesLiveData: LiveData<Array<String>>
        get() = waterCompanyNames
    val bottleTypesLiveData: LiveData<Array<BottleType>>
        get() = bottleTypes

    private var selectedWaterIndex: Int? = null

    init {
        fetchAllWater()
        fetchWaterCompanyNames()
    }

    private fun fetchAllWater() {
        // TODO: Get user ID
        // TODO: Use coroutines
        water.value = waterConsumptionRepository.getAllWaterForUser("id")
    }

    private fun fetchWaterCompanyNames() {
        waterCompanyNames.value = waterCompanyRepository.getAllCompanyNames().sortedArray()
    }


    fun fetchBottleTypes(index: Int) {
        if (selectedWaterIndex == null || waterCompanyNames.value == null) {
            return
        }
        bottleTypes.value = waterCompanyRepository.getBottleTypesForCompany(waterCompanyNames.value!!.elementAt(index))
    }

    fun getSelectedWaterBrandIndex(): Int? {
        return selectedWaterIndex
    }

    fun setSelectedWaterIndex(index: Int) {
        selectedWaterIndex = index
    }

    /**
     * Simple {ViewModelProvider.Factory} method to instantiate a new {WaterViewModel}
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get application object
                val application = checkNotNull(this[APPLICATION_KEY] as Application)

                // Create ViewModel dependencies
                val internalConsumptionStorageService: InternalConsumptionStorageService =
                    InternalConsumptionStorageService(application)
                val waterRepositoryLocalStorage: WaterConsumptionRepositoryInterface =
                    WaterConsumptionRepositoryLocalStorage(internalConsumptionStorageService)

                val internalCompanyStorageService: InternalCompanyStorageService =
                    InternalCompanyStorageService(application)
                val waterCompanyRepository: WaterCompanyRepositoryInterface =
                    WaterCompanyRepositoryLocalStorage(internalCompanyStorageService)

                // Create view model
                WaterViewModel(waterRepositoryLocalStorage, waterCompanyRepository)
            }
        }
    }
}