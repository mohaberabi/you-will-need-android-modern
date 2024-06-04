package com.example.fcm.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable

fun TokenScreen(

    onSubmit: () -> Unit,
    token: String,
    onTokenChange: (String) -> Unit
) {


    val clipBoardManager = LocalClipboardManager.current

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column {

        BasicTextField(
            value = token,
            onValueChange = onTokenChange,
        )



        Row(modifier = Modifier.fillMaxWidth()) {

            OutlinedButton(
                onClick = {
                    scope.launch {
                        val localToken = Firebase.messaging.token.await()

                        clipBoardManager.setText(AnnotatedString(localToken))
                        Toast.makeText(context, "Copied Token ${localToken}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ) {

                Text(text = "Copy Token")
            }

            OutlinedButton(
                onClick = {

                    onSubmit()
                }
            ) {

                Text(text = "Submit")
            }
        }

    }
}