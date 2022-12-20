package com.example.drinktracker.repository.definitions

import com.example.drinktracker.models.BottleType
import com.example.drinktracker.models.Water

interface WaterConsumptionRepositoryInterface {
    fun getAllWaterForUser(id: String): Array<Water>
    fun addWaterToUser(id: String, water: Water)
}