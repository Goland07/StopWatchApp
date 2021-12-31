package com.andreygolovin.stopwatchapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andreygolovin.stopwatchapp.databinding.ActivityMainBinding
import com.andreygolovin.stopwatchapp.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.ticker.observe(this) {
            binding.textTime.text = it
        }

        with(binding) {
            buttonStart.setOnClickListener { mainViewModel.buttonStart() }
            buttonPause.setOnClickListener { mainViewModel.buttonPause() }
            buttonStop.setOnClickListener { mainViewModel.buttonStop() }
        }
    }
}