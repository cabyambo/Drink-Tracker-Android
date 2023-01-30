package com.yambo.drinktracker.repository.localstorage

import com.yambo.drinktracker.service.localstorage.InternalConsumptionStorageService
import com.yambo.drinktracker.models.Water
import com.yambo.drinktracker.repository.definitions.WaterConsumptionRepositoryInterface

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