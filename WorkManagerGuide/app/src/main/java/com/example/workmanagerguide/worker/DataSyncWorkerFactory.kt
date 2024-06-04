package com.example.workmanagerguide.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.workmanagerguide.repository.DataSyncRepository

class DataSyncWorkerFactory(
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return when (workerClassName) {
            DataSyncWorker::class.java.name -> {
                DataSyncWorker(appContext, workerParameters)
            }

            else -> throw IllegalArgumentException("ERROR CREATING WORKER")
        }
    }
}
