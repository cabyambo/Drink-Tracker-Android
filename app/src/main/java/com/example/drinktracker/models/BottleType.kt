package com.example.drinktracker.models

data class BottleType(
    val size: Float,
    val unit: Unit
) {
    override fun toString(): String {
        return "$size ${unit.string}"
    }
}
