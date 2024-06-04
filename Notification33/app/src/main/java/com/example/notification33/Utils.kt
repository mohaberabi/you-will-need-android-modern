package com.example.notification33

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build


fun doesNeedNotificationPermission(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU