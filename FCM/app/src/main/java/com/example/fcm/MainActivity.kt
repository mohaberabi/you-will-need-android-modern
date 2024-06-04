package com.example.fcm

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fcm.screens.MessageScreen
import com.example.fcm.screens.TokenScreen
import com.example.fcm.ui.theme.FCMTheme
import com.example.fcm.viewmodel.NotiViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: NotiViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission()
        setContent {
            FCMTheme {


                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {


                    val state = viewModel.state.collectAsState().value
                    if (state.isTypingToken) {

                        TokenScreen(
                            onSubmit = {
                                viewModel.onSubmitToken()
                            },
                            onTokenChange = {
                                viewModel.onRemoteTokenChanged(it)
                            },
                            token = state.remoteToken,

                            )


                    } else {
                        MessageScreen(
                            messageText = state.messageText,
                            onMessageChanged = viewModel::onMessageChanged,
                            onMessageSend = { viewModel.sendMessage(false) }) {
                        }
                    }


                }

            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {


            val hasPermisison =
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermisison) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0,
                )
            }
        }
    }
}

