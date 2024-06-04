package com.example.workmanagerguide.repository

import android.app.Application
import kotlinx.coroutines.delay

class DataSyncRepository(
) {


    suspend fun syncData() {
        delay(3000L)
        println("synced")
    }
}