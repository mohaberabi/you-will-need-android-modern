package com.example.localnotification.counter_reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.localnotification.noti.Counter
import com.example.localnotification.noti.showCounterNotification

class CounterNotificationReciever : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intnet: Intent?
    ) {

        context.showCounterNotification(++Counter.value)

    }
}