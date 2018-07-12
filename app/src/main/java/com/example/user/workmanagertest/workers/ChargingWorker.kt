package com.example.user.workmanagertest.workers

import android.util.Log
import androidx.work.Worker

class ChargingWorker : Worker() {
    override fun doWork(): Result {
        Log.d("vad", "Hi, i am worker and i know that your device is charging!")
        return Result.SUCCESS
    }
}