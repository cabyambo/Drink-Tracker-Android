package com.yambo.drinktracker.models

data class BottleSize(
    val size: Float,
    val unit: Unit
) {
    override fun toString(): String {
        return "$size ${unit.unitString}"
    }
}
