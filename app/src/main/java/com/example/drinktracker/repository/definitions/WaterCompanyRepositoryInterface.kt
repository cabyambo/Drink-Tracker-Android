package com.example.drinktracker.repository.definitions

import com.example.drinktracker.models.BottleSize

interface WaterCompanyRepositoryInterface {
    fun getAllCompanyNames() : Array<String>
    fun getBottleSizesForCompany(companyName: String) : Array<BottleSize>
    fun getAllBottleSizes() : Array<BottleSize>
}