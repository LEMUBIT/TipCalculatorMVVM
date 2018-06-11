package com.example.lemuel.tipcalculatormvvm.viewmodel

import android.app.Application
import com.example.lemuel.tipcalculatormvvm.R
import com.example.lemuel.tipcalculatormvvm.model.RestaurantCalculator
import com.example.lemuel.tipcalculatormvvm.model.TipCalculation
import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.*
import junit.framework.TestCase.assertEquals
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTestMockito {
    lateinit var calculatorViewModel: CalculatorViewModel

    /*A mock object is a dummy implementation for an interface or a class in which you define the
    output of certain method calls. Mock objects are configured to perform a certain behavior during a test.
    They typically record the interaction with the system and tests can validate that.
    */

    @Mock
    lateinit var mockCalculator: RestaurantCalculator

    @Mock
    lateinit var application: Application

    @Before
    fun setup() {
        /*
        This initializes any member variables which had been annotated with the @mock making them mock objects*/
        MockitoAnnotations.initMocks(this)

        //ensures that we are evoking application.getString with the expected string resource
        //should mirror how it would be called in test e.g This would be called in the constructor in the ViewModel class
        stubResource(0.0, "$0.0")

        calculatorViewModel = CalculatorViewModel(application, mockCalculator)
    }

    /*
    makes sure we call this with the values we expect from our test based
    on how we've set up our model and mock and all the inputs, if our viewModel
    calls this function with any other numeric values, our test would fail
    */
    private fun stubResource(given: Double, returnStub: String) {
        /*
        given a double, returns an expected string resource
        */
        `when`(application.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    @Test
    fun testCalculateTip() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputPercentage = "15"

        //mock the expected output
        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)

        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)

        //ensures that we are evoking application.getString with the expected string resource
        stubResource(10.0, "$10.00")
        stubResource(1.5, "$1.50")
        stubResource(11.5, "$11.50")

        calculatorViewModel.calculateTip()

        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)

    }


    /*
    verify viewmodel doesn't call calculate tip if inputs are invalid
    */
    @Test
    fun testCalculateBadTipPercentage() {
        /*
        validate that 'calculateTip is never called for any Double or Int if percentage is bad
        */
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputPercentage = ""

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(
                ArgumentMatchers.anyDouble(),
                ArgumentMatchers.anyInt()
        )

    }

    @Test
    fun testCalculateBadCheckAmount() {
        /*
        validate that 'calculateTip is never called for any Double or Int if checkAmount is invalid
        */
        calculatorViewModel.inputCheckAmount = ""
        calculatorViewModel.inputPercentage = "15"

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(
                ArgumentMatchers.anyDouble(),
                ArgumentMatchers.anyInt()
        )

    }


}