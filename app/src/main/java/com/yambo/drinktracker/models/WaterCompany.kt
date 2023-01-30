package com.yambo.drinktracker.models

data class WaterCompany(
    val companyName: String,
    val bottleSizes: Array<BottleSize>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WaterCompany

        if (companyName != other.companyName) return false
        if (!bottleSizes.contentEquals(other.bottleSizes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = companyName.hashCode()
        result = 31 * result + bottleSizes.contentHashCode()
        return result
    }
}
