package com.example.drinktracker.repository

import com.example.drinktracker.models.BottleType
import com.example.drinktracker.models.Water

interface WaterRepositoryInterface {
    fun getAllWaterForUser(id: String): Array<Water>
    fun getBottleTypesForCompany(company: String): Array<BottleType>
    fun addWaterToUser(id: String, water: Water)
}