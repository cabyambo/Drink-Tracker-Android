package com.example.drinktracker.repository.localstorage

import com.example.drinktracker.service.localstorage.InternalConsumptionStorageService
import com.example.drinktracker.models.Water
import com.example.drinktracker.repository.definitions.WaterConsumptionRepositoryInterface

class WaterConsumptionRepositoryLocalStorage(
    private val internalConsumptionStorageService: InternalConsumptionStorageService
) : WaterConsumptionRepositoryInterface {
    override fun getAllWaterForUser(id: String): Array<Water> {
        return internalConsumptionStorageService.getAllWater();
    }

    override fun addWaterToUser(id: String, water: Water) {
        internalConsumptionStorageService.addWater(water)
    }
}