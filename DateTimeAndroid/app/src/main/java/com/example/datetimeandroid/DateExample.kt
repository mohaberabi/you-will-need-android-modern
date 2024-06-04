package com.example.datetimeandroid

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun HomeScreen() {


    val date = remember {

        LocalDate.now()
    }


    val dateZoned = remember {

        ZonedDateTime.ofInstant(

            Instant.ofEpochMilli(System.currentTimeMillis()),
            ZoneId.of("Africa/Cairo")
        )

    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(text = "LocalDate.now() ${date.toString()}")
        Text(text = "AfricaCairoTime.now() ${dateZoned.toString()}")

    }
}