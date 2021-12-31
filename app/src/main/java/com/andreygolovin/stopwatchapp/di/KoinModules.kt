package com.andreygolovin.stopwatchapp.di

import com.andreygolovin.stopwatchapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModel = module {
    viewModel { MainViewModel() }
}