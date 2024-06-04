package com.example.formvalidation.prensetation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.formvalidation.prensetation.viewmodel.AuthEvent
import com.example.formvalidation.prensetation.viewmodel.AuthViewModel
import com.example.formvalidation.prensetation.viewmodel.SubmissionEvent
import kotlinx.coroutines.flow.collect


@Composable

fun AuthScreen(

    viewModel: AuthViewModel
) {

    val state = viewModel.state.collectAsState().value

    val event = viewModel.event
    val context = LocalContext.current

    LaunchedEffect(context) {
        event.collect { value ->
            if (value is SubmissionEvent.Done) {
                Toast.makeText(context, "Auth is Done ", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppTextField(
            value = state.email,
            onChanged = {
                viewModel.onEvent(AuthEvent.OnEmailChanged(it))
            },
            isError = state.emailError != null,
            placeHolder = "Email",
            error = state.emailError,
            type = KeyboardType.Email
        )

        AppTextField(
            value = state.password,
            suffix = {
                PasswordIcon {
                    viewModel.onEvent(AuthEvent.OnShowPasswordChanged)
                }
            },
            isSecret = !state.showPassword,
            onChanged = {
                viewModel.onEvent(AuthEvent.OnPasswordChanged(it))
            },
            isError = state.passwordError != null,
            placeHolder = "Password",
            error = state.passwordError,
        )
        AppTextField(
            suffix = {
                PasswordIcon {
                    viewModel.onEvent(AuthEvent.OnShowRepeatedPasswordChanged)
                }
            },
            isSecret = !state.showRepeatedPassword,


            value = state.repeatedPassword,

            onChanged = {
                viewModel.onEvent(AuthEvent.OnRepeatedPasswordChanged(it))
            },
            isError = state.repeatedPasswordError != null,
            placeHolder = "Confirm password",
            error = state.repeatedPasswordError,
        )



        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Accept terms")
            Checkbox(

                checked = state.acceptTerms, onCheckedChange = {
                    viewModel.onEvent(
                        AuthEvent.OnTermsChanged
                    )
                }

            )

        }
        if (state.termsError != null) {
            Text(
                text = state.termsError,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red)
            )

        }


        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.submit()
        }
        ) {
            Text(text = "Submit")
        }
    }

}


@Composable
fun AppTextField(
    value: String,
    onChanged: (String) -> Unit,
    isError: Boolean,
    placeHolder: String,
    type: KeyboardType = KeyboardType.Text,

    error: String? = null,
    isSecret: Boolean = false,
    suffix: (@Composable () -> Unit)? = null,

    ) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (isSecret) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = type),
            isError = isError,
            placeholder = { Text(text = placeHolder) },
            value = value,
            onValueChange = onChanged,
            suffix = suffix
        )
        if (isError) {
            Text(
                text = error ?: "",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red)
            )

        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable

fun PasswordIcon(

    isSecret: Boolean = true,
    onChanged: () -> Unit,
) {

    IconButton(onClick = {
        onChanged()
    }) {

        Icon(
            imageVector = Icons.Default.Lock,
            modifier = Modifier.size(14.dp),
            contentDescription = null
        )
    }
}