package com.yambo.drinktracker.service.localstorage

import android.app.Application
import com.yambo.drinktracker.models.WaterCompany
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class InternalCompanyStorageService(
    private val context: Application
) {
    fun getAllWaterCompanies(): Array<WaterCompany> {
        // Read from the stored file
        try {
            val inputStream: InputStream = context.assets.open("WaterCompanyList.txt")
            val waterCompanyList: ArrayList<WaterCompany> = ArrayList()
            val gson = Gson()
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    val waterCompany: WaterCompany = gson.fromJson(line, WaterCompany::class.java)
                    waterCompanyList.add(waterCompany)
                }
            }
            return waterCompanyList.toTypedArray()
        } catch (e: IOException) {
            throw IOException("Error reading water company data from internal storage.", e)
        }
        // Create an array of company names
    }
}