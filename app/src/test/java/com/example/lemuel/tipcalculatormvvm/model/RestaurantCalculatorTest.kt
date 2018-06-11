package com.example.lemuel.tipcalculatormvvm.model

import junit.framework.Assert
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class RestaurantCalculatorTest {
    lateinit var calculator: RestaurantCalculator


    @Before
    fun setup() {
        calculator = RestaurantCalculator()
    }

    @Test
    fun testCalculateTip() {
        val baseTc = TipCalculation(10.00)

        //Kotlin data class has 'copy' function that cann have copies
        //of original plus any info that is overriden

        val testVals = listOf(
                baseTc.copy(tipPct = 25, tipAmount = 2.5, grandTotal = 12.50),
                baseTc.copy(tipPct = 15, tipAmount = 1.5, grandTotal = 11.50),
                baseTc.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.80)
        )

        //loop through and test each case
        testVals.forEach {
            assertEquals(it, calculator.calculateTip(it.checkAmount, it.tipPct))
        }


    }
}