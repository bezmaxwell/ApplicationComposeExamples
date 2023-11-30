package com.example.applicationcomposeexamples.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.applicationcomposeexamples.model.RentalInfo

class ViewModelCHatGPT {
}

class RentalViewModel : ViewModel() {
    val rentalInfo = mutableStateOf<RentalInfo?>(null)

    fun calculateReturnDate(rentalDays: Int) {
        val currentDate = System.currentTimeMillis()
        val returnDateMillis = currentDate + rentalDays * 24 * 60 * 60 * 1000L
        val rentalInfoValue = rentalInfo.value
        if (rentalInfoValue != null) {
            rentalInfo.value = rentalInfoValue.copy(returnDate = returnDateMillis.toString())
        }
    }
}