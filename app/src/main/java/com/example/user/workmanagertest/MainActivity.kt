package com.example.user.workmanagertest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.user.workmanagertest.workers.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btSingle.setOnClickListener { startSingleWorker() }
        btPeriodic.setOnClickListener { startPeriodicWorker() }
        btSingleWithConstraint.setOnClickListener { startSingleWithConstraintWorker() }
        btChained.setOnClickListener { startChainedWorker() }
        btChainedWithErrorOnSecond.setOnClickListener { startChainedWithErrorOnSecondWorker() }
    }

    private fun startSingleWorker() {
        val singleWork = OneTimeWorkRequest.Builder(SingleWorker::class.java).build()
        WorkManager.getInstance()?.enqueue(singleWork)
    }

    private fun startPeriodicWorker() {
        val photoCheckBuilder =
                PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 16, TimeUnit.MINUTES)

        val photoCheckWork = photoCheckBuilder.build()
        WorkManager.getInstance()?.enqueue(photoCheckWork)
    }

    private fun startSingleWithConstraintWorker() {
        val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        val singleWorkWithConstraint = OneTimeWorkRequest.Builder(ChargingWorker::class.java)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance()?.enqueue(singleWorkWithConstraint)
    }

    private fun startChainedWorker() {
        val chainedWork = OneTimeWorkRequest.Builder(ChainedWorker::class.java).build()
        val singleWork = OneTimeWorkRequest.Builder(SingleWorker::class.java).build()
        val successWork = OneTimeWorkRequest.Builder(SuccessWorker::class.java).build()
        WorkManager.getInstance()
                ?.beginWith(chainedWork)
                ?.then(singleWork)
                ?.then(successWork)
                ?.enqueue()
    }

    private fun startChainedWithErrorOnSecondWorker() {
        val chainedWork = OneTimeWorkRequest.Builder(ChainedWorker::class.java).build()
        val singleWork = OneTimeWorkRequest.Builder(SingleWorker::class.java).build()
        val errorWork = OneTimeWorkRequest.Builder(ErrorWorker::class.java).build()
        val successWork = OneTimeWorkRequest.Builder(SuccessWorker::class.java).build()
        WorkManager.getInstance()
                ?.beginWith(chainedWork)
                ?.then(errorWork)
                ?.then(singleWork)
                ?.then(successWork)
                ?.enqueue()
    }
}
