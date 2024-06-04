package com.example.localnotification.noti

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.localnotification.LocalNotiApp
import com.example.localnotification.MainActivity
import com.example.localnotification.R
import com.example.localnotification.counter_reciever.CounterNotificationReciever
import com.example.localnotification.util.getNotificationManager
import com.example.localnotification.util.needsPendingFlagsIntent


fun Context.showCounterNotification(counter: Int) {

    val notiManager = getNotificationManager()

    val activityIntent = Intent(this, MainActivity::class.java)

    val counterBroadcaseIntent = Intent(
        this,
        CounterNotificationReciever::class.java
    )
    val counterPendingIntent = PendingIntent.getBroadcast(
        this, 2,
        counterBroadcaseIntent,
        if (needsPendingFlagsIntent()) PendingIntent.FLAG_IMMUTABLE else 0

    )
    val activityPendingIntent = PendingIntent.getActivity(
        this,
        1,
        activityIntent,
        if (needsPendingFlagsIntent()) PendingIntent.FLAG_IMMUTABLE else 0
    )


    val notification = NotificationCompat.Builder(
        this,
        LocalNotiApp.NOTIFICATION_CHANNEL_ID
    ).setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Increment The counter loser ")
        .setContentText("Counter is ${counter} , ... loser ")
        .setContentIntent(activityPendingIntent)
        .addAction(
            R.drawable.ic_launcher_background,
            "Increment ",
            counterPendingIntent,
        ).build()


    if (isNotificationPermisisonAllowed()) {
        notiManager.notify(1, notification)
    }

}

private fun Context.isNotificationPermisisonAllowed() = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.POST_NOTIFICATIONS
) == PackageManager.PERMISSION_GRANTED