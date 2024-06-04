package com.example.notification33

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class Notification33App : Application() {

    companion object {
        const val NOTIFICATION_ID = "noti33appId"
        const val NOTIFICATION_NAME = "noti33appName"

    }

    override fun onCreate() {
        super.onCreate()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createAppNotificationChannel(notificationManager)

    }


    private fun createAppNotificationChannel(notificationManager: NotificationManager) {
        if (doesNeedNotificationPermission()) {
            val channel = NotificationChannel(
                NOTIFICATION_ID,
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)


        }
    }
}