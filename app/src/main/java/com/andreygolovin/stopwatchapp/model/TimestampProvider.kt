package com.andreygolovin.stopwatchapp.model

interface TimestampProvider {
    fun getMilliseconds(): Long
}