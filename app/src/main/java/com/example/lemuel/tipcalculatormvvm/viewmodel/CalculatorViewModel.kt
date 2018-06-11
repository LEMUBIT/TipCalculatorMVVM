/*The ViewModel connects the View and the Model, providing the necessary inputs and actions for the View to bind to.
It also performs any presentation logic necessary.*/
package com.example.lemuel.tipcalculatormvvm.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import android.util.Log
import com.example.lemuel.tipcalculatormvvm.R
import com.example.lemuel.tipcalculatormvvm.model.RestaurantCalculator
import com.example.lemuel.tipcalculatormvvm.model.TipCalculation

/*has a constructor with default 'restaurantCalculator' value 'RestaurantCalculator()'*/
class CalculatorViewModel(val app: Application, val restaurantCalculator: RestaurantCalculator = RestaurantCalculator()) : BaseObservable() {

    var inputCheckAmount = ""
    var inputPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalDollarAmount = ""


    init {
        /*set initial value of views to 0.00*/
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tipCalculation: TipCalculation) {
        /*Use application object to get string resource and format strings to dollar format*/
        outputCheckAmount = app.getString(R.string.dollar_amount, tipCalculation.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount, tipCalculation.tipAmount)
        outputTotalDollarAmount = app.getString(R.string.dollar_amount, tipCalculation.grandTotal)
    }

    fun calculateTip() {

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val checkInputPercentage = inputPercentage.toIntOrNull()

        if (checkAmount != null && checkInputPercentage != null) {
            updateOutputs(restaurantCalculator.calculateTip(checkAmount, checkInputPercentage))
            clearInputs()
        }


    }

    /*uses Two way dataBinding to update Text views with variables*/
    /*e.g @={vm.inputCheckAmount}, see XML*/
    private fun clearInputs() {

        inputCheckAmount = "0.00"
        inputPercentage = "0"

        /*from Base Observable, used to update views after a change has occurred*/
        notifyChange()
    }


}