package com.andreygolovin.stopwatchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.andreygolovin.stopwatchapp.domain.ElapsedTimeCalculator
import com.andreygolovin.stopwatchapp.domain.StopwatchListOrchestrator
import com.andreygolovin.stopwatchapp.domain.StopwatchStateCalculator
import com.andreygolovin.stopwatchapp.domain.StopwatchStateHolder
import com.andreygolovin.stopwatchapp.model.TimestampProvider
import com.andreygolovin.stopwatchapp.utils.TimestampMillisecondsFormatter
import kotlinx.coroutines.cancel

class MainViewModel : ViewModel() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        viewModelScope
    )

    val ticker: LiveData<String> = stopwatchListOrchestrator.ticker.asLiveData()

    fun buttonStart() {
        stopwatchListOrchestrator.start()
    }

    fun buttonPause() {
        stopwatchListOrchestrator.pause()
    }

    fun buttonStop() {
        stopwatchListOrchestrator.stop()
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}