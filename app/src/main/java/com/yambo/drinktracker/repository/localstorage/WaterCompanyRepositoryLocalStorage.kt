package com.yambo.drinktracker.repository.localstorage

import com.yambo.drinktracker.models.BottleSize
import com.yambo.drinktracker.models.WaterCompany
import com.yambo.drinktracker.repository.definitions.WaterCompanyRepositoryInterface
import com.yambo.drinktracker.service.localstorage.InternalCompanyStorageService

class WaterCompanyRepositoryLocalStorage(
    private val internalCompanyStorageService: InternalCompanyStorageService
) : WaterCompanyRepositoryInterface {
    // TODO: Implement caching.
    //  These values will not change on a daily basis.fd
    override fun getAllCompanyNames(): Array<String> {
        val waterCompanies: Array<WaterCompany> = internalCompanyStorageService.getAllWaterCompanies()
        return waterCompanies.map { it.companyName }.toTypedArray()
    }

    override fun getBottleSizesForCompany(companyName: String): Array<BottleSize> {
        val waterCompanies: Array<WaterCompany> = internalCompanyStorageService.getAllWaterCompanies()
        return waterCompanies.filter { it.companyName == companyName }.first().bottleSizes
    }

    override fun getAllBottleSizes(): Array<BottleSize> {
        val waterCompanies: Array<WaterCompany> = internalCompanyStorageService.getAllWaterCompanies()
        return waterCompanies.flatMap { it.bottleSizes.asIterable() }.toTypedArray()
    }
}