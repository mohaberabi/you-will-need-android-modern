package com.example.fcm.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable

fun MessageScreen(
    messageText: String,
    onMessageChanged: (String) -> Unit,
    onMessageSend: () -> Unit,
    onMessageBroadCast: () -> Unit

) {


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        OutlinedTextField(
            value = messageText,
            placeholder = { Text(text = "Enter message to send ") },
            onValueChange = onMessageChanged
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {


            IconButton(onClick = { onMessageSend() }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }

            IconButton(onClick = { onMessageBroadCast() }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
            }
        }


    }
}