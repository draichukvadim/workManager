package com.example.user.workmanagertest.workers

import android.util.Log
import androidx.work.Worker

class SuccessWorker : Worker() {
    override fun doWork(): Result {
        Log.d("vad", "Success!")
        return Result.FAILURE
    }
}