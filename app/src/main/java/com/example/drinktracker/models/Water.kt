package com.example.drinktracker.models

import com.google.gson.Gson

data class Water(
    val companyName: String,
    val bottleType: BottleType
) {
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }
}
