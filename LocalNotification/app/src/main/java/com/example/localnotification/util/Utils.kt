package com.example.localnotification.util

import android.app.NotificationManager
import android.content.Context
import android.os.Build


fun needsNotificationPermission(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
//the flags defines what happens when it's already done

fun needsPendingFlagsIntent(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun Context.getNotificationManager(): NotificationManager =
    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager