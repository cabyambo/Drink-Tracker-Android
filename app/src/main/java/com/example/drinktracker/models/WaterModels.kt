package com.example.drinktracker.models

data class Dasani(
    val sizes: ArrayList<Size> =
        arrayListOf(
            Size(8f, Unit.OUNCE),
            Size(12f, Unit.OUNCE),
            Size(1f, Unit.GALLON)
        ),
)

data class Aquafina(
    val sizes: ArrayList<Size> =
        arrayListOf(
            Size(8f, Unit.OUNCE),
            Size(12f, Unit.OUNCE),
            Size(1f, Unit.GALLON)
        ),
)

data class Zephyrhills(
    val sizes: ArrayList<Size> =
        arrayListOf(
            Size(8f, Unit.OUNCE),
            Size(12f, Unit.OUNCE),
            Size(1f, Unit.GALLON)
        ),
)