package com.yambo.drinktracker.repository.definitions

import com.yambo.drinktracker.models.Water

interface WaterConsumptionRepositoryInterface {
    fun getAllWaterForUser(id: String): Array<Water>
    fun addWaterToUser(id: String, water: Water)
}