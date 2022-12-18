package com.example.drinktracker.repository

import com.example.drinktracker.service.InternalStorageService
import com.example.drinktracker.models.BottleType
import com.example.drinktracker.models.Water

class WaterRepositoryLocalStorage(
    private val internalStorageService: InternalStorageService
) : WaterRepositoryInterface {
    override fun getAllWaterForUser(id: String): Array<Water> {
        return internalStorageService.getAllWater();
    }

    override fun getBottleTypesForCompany(company: String): Array<BottleType> {
        return internalStorageService.getBottleTypesForBrand(company)
    }

    override fun addWaterToUser(id: String, water: Water) {
        internalStorageService.addWater(water)
    }
}