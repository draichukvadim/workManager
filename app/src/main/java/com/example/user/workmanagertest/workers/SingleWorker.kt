package com.example.user.workmanagertest.workers

import android.util.Log
import androidx.work.Worker

class SingleWorker : Worker() {
    override fun doWork(): Result {
        Log.d("vad", "Hi, i am single worker!")
        return Result.SUCCESS
    }
}