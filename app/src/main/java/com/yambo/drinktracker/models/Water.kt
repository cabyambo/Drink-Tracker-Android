package com.yambo.drinktracker.models

import com.google.gson.Gson

data class Water(
    val companyName: String,
    val bottleSize: BottleSize
) {
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }
}
