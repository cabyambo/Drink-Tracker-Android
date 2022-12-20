package com.example.drinktracker.repository.definitions

import com.example.drinktracker.models.BottleType

interface WaterCompanyRepositoryInterface {
    fun getAllCompanyNames() : Array<String>
    fun getBottleTypesForCompany(companyName: String) : Array<BottleType>
    fun getAllBottleTypes() : Array<BottleType>
}