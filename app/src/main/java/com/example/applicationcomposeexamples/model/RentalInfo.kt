package com.example.applicationcomposeexamples.model

data class RentalInfo(
    val imageResId: Int,
    val name: String,
    val rentalDate: String,
    val returnDate: String,
    val rentalPrice: Double
)