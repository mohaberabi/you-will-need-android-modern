package com.example.workmanagerguide.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerguide.repository.DataSyncRepository

class DataSyncWorker(
    context: Context,
    params: WorkerParameters,

    ) : CoroutineWorker(context, params) {


    override suspend fun doWork(): Result {


        return try {
            val dataSyncRepository = DataSyncRepository()
            dataSyncRepository.syncData()
            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}