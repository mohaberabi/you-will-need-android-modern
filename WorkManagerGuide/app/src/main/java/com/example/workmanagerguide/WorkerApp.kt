package com.example.workmanagerguide

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.BackoffPolicy
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanagerguide.repository.DataSyncRepository
import com.example.workmanagerguide.worker.DataSyncWorker
import com.example.workmanagerguide.worker.DataSyncWorkerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)

class WorkerApp : Application(), Configuration.Provider {


    private val appScope = CoroutineScope(Dispatchers.Default)


    private val workerFactory: DataSyncWorkerFactory by lazy {
        DataSyncWorkerFactory()
    }

    override lateinit var workManagerConfiguration: Configuration

    override fun onCreate() {
        super.onCreate()
        workManagerConfiguration = Configuration.Builder().setWorkerFactory(workerFactory).build()
        delayInit()
    }


    private fun delayInit() {
        appScope.launch {
            initWorker()
        }
    }


    private fun initWorker() {
        val workRequest = OneTimeWorkRequestBuilder<DataSyncWorker>()
            /**
             * if you returned a Result.retry() from the worker dowork function
             * this [setBackoffCriteria] will retry  to do each 15 seconds until the work is done
             * in case of linear  the passed seconds to Duration.ofSeconds will try eg[15] will try each
             * 15 seconds
             * if you set the policy to [Exponent] it will double the time each time , in
             * eg : time =15 , 15->30->60...and soo on
             */
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(15)
            )
            .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)


    }
}
