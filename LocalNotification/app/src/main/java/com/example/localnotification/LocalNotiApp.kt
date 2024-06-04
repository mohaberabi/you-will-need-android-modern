package com.example.localnotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.localnotification.util.needsNotificationPermission

class LocalNotiApp : Application() {

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "MohabTheLoserBoy"
    }

    override fun onCreate() {
        val notiManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notiManager)
        super.onCreate()
    }


    fun createNotificationChannel(notiManager: NotificationManager) {
        if (needsNotificationPermission()) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_ID,
                NotificationManager.IMPORTANCE_HIGH
            )
            notiManager.createNotificationChannel(channel)
        }
    }

}