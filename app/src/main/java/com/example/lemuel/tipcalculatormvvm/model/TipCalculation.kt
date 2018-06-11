package com.example.lemuel.tipcalculatormvvm.model

//We frequently create classes whose main purpose is to hold data.
// In such a class some standard functionality and utility functions are often mechanically derivable from the data.
// In Kotlin, this is called a data class and is marked as data
data class TipCalculation(
        val checkAmount: Double = 0.0,
        val tipPct: Int = 0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0
)