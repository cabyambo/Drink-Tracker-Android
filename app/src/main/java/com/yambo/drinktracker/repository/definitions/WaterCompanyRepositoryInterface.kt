package com.yambo.drinktracker.repository.definitions

import com.yambo.drinktracker.models.BottleSize

interface WaterCompanyRepositoryInterface {
    fun getAllCompanyNames() : Array<String>
    fun getBottleSizesForCompany(companyName: String) : Array<BottleSize>
    fun getAllBottleSizes() : Array<BottleSize>
    // Test comment
}