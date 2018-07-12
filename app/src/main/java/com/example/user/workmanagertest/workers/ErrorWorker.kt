package com.example.user.workmanagertest.workers

import android.util.Log
import androidx.work.Worker

class ErrorWorker : Worker() {
    override fun doWork(): Result {
        Log.d("vad", "Babah!")
        return Result.FAILURE
    }
}