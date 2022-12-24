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
    private val companyNames = MutableLiveData<Array<String>>()
    private val bottleTypes = MutableLiveData<Array<BottleSize>>()

    val waterLiveData: LiveData<Array<Water>>
        get() = water
    val companyNamesLiveData: LiveData<Array<String>>
        get() = companyNames
    val bottleSizesLiveData: LiveData<Array<BottleSize>>
        get() = bottleTypes

    private var selectedCompanyIndex: Int? = null
    private var selectedBottleSizeIndex: Int? = null

    init {
        fetchAllWater()
        fetchCompanyNames()
    }

    private fun fetchAllWater() {
        // TODO: Get user ID
        // TODO: Use coroutines
        water.value = waterConsumptionRepository.getAllWaterForUser("id")
    }

    private fun fetchCompanyNames() {
        companyNames.value = waterCompanyRepository.getAllCompanyNames().sortedArray()
    }

    fun fetchBottleSizes() {
        if (selectedCompanyIndex == null || companyNames.value == null) {
            return
        }
        bottleTypes.value = waterCompanyRepository.getBottleSizesForCompany(companyNames.value!!.elementAt(selectedCompanyIndex!!))
    }

    fun getSelectedCompanyIndex(): Int? {
        return selectedCompanyIndex
    }

    fun getSelectedBottleSizeIndex(): Int? {
        return selectedBottleSizeIndex
    }

    fun setSelectedCompanyIndex(index: Int) {
        // If already selected item isn't being re-selected,
        // set the size index to the "start"
        if (index != selectedCompanyIndex) {
            selectedBottleSizeIndex = 0
        }
        selectedCompanyIndex = index
    }

    fun setSelectedBottleSizeIndex(index: Int) {
        selectedBottleSizeIndex = index
    }

    fun addWaterToUser(water: Water) {
        val id = "id"
        waterConsumptionRepository.addWaterToUser(id, water)
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