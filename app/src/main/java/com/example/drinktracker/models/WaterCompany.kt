package com.example.drinktracker.models

data class WaterCompany(
    val companyName: String,
    val bottleTypes: Array<BottleType>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WaterCompany

        if (companyName != other.companyName) return false
        if (!bottleTypes.contentEquals(other.bottleTypes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = companyName.hashCode()
        result = 31 * result + bottleTypes.contentHashCode()
        return result
    }
}
