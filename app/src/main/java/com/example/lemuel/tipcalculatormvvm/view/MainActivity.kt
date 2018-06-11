package com.example.lemuel.tipcalculatormvvm.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.lemuel.tipcalculatormvvm.R
import com.example.lemuel.tipcalculatormvvm.databinding.ActivityMainBinding
import com.example.lemuel.tipcalculatormvvm.viewmodel.CalculatorViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.vm = CalculatorViewModel(application)

        setSupportActionBar(binding.toolbar)

    }


}
