package com.example.lemuel.tipcalculatormvvm.model

import java.math.RoundingMode

class RestaurantCalculator {

    /*
    calculate Tip using the amount and the percentage of the Tip*/
    fun calculateTip(checkAmount: Double, tipPct: Int): TipCalculation {
        val tipAmount = (checkAmount * (tipPct.toDouble() / 100.00))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount,
                tipPct,
                tipAmount,
                grandTotal
        )
    }

}