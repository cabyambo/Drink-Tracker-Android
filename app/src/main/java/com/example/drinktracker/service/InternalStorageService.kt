package com.example.drinktracker.service

import android.app.Application
import android.util.Log
import com.example.drinktracker.utils.Constants
import com.example.drinktracker.models.BottleType
import com.example.drinktracker.models.Water
import com.google.gson.Gson
import java.io.*

class InternalStorageService(
    private val context: Application
) {
    fun getAllWater() : Array<Water> {
        try {
            val directory = File(context.filesDir, Constants.LOCAL_STORAGE_WATER_DIRECTORY_NAME)
            if (!directory.exists()) {
                return emptyArray()
            }
            val file = File(directory, Constants.LOCAL_STORAGE_WATER_FILE_NAME)
            if (!file.exists()) {
                return emptyArray()
            }
            val fileReader = FileReader(file)
            val waterList: ArrayList<Water> = ArrayList()
            val gson = Gson()
            fileReader.forEachLine {
                val water = gson.fromJson(it, Water::class.java)
                waterList.add(water)
            }
            return waterList.toTypedArray()
        } catch (e: IOException) {
            throw IOException("Error reading water from internal storage.", e)

        }
    }

    fun addWater(water: Water) {
        try {
            Log.d("yest", context.filesDir.toString())
            val directory = File(context.filesDir, Constants.LOCAL_STORAGE_WATER_DIRECTORY_NAME)
            if (!directory.exists()) {
                directory.mkdir()
            }
            val file = File(directory, Constants.LOCAL_STORAGE_WATER_FILE_NAME)
            val fileWriter = FileWriter(file, true)
            fileWriter.append("$water\n")
            fileWriter.flush()
            fileWriter.close()
        } catch (e: IOException) {
            throw IOException("Error adding water to internal storage.", e)
        }
    }

    fun getBottleTypesForBrand(brand: String) : Array<BottleType> {
        return emptyArray()
    }
}