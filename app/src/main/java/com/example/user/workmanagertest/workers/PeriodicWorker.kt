package com.example.user.workmanagertest.workers

import android.util.Log
import androidx.work.Worker

class PeriodicWorker : Worker() {
    override fun doWork(): Result {
        Log.d("vad", "Hi, i am periodic and i like periods!")
        return Result.SUCCESS
    }

}