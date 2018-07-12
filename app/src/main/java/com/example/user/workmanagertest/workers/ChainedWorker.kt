package com.example.user.workmanagertest.workers

import android.util.Log
import androidx.work.Worker

class ChainedWorker : Worker() {
    override fun doWork(): Result {
        Log.d("vad", "Hi, its chain time!")
        return Result.SUCCESS
    }
}