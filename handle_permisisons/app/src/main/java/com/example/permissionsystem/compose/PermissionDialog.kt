package com.example.permissionsystem.compose

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.permissionsystem.util.AppPermissions
import com.example.permissionsystem.util.CameraPermissionExplainer
import com.example.permissionsystem.util.PermissionExplainer


@Composable


fun PermissionDialog(
    permission: PermissionExplainer,
    isDeniedForever: Boolean,
    onDismiss: () -> Unit,
    onOk: () -> Unit,
    onGoSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {


    AlertDialog(

        onDismissRequest = {
            onDismiss()

        },

        confirmButton = {
            Text(
                text = if (isDeniedForever) "Open Settings " else "Allow ",
                modifier = Modifier.clickable {

                    if (isDeniedForever) {
                        onGoSettings()
                    } else {
                        onOk()
                    }

                },
            )
        },

        title = {
            Text(text = "Permisison Required")

        },
        text = {

            Text(text = "${permission.getString(isDeniedForever).asString()}")
        },
        dismissButton = {

            Text(
                text = "Not Now ",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        },

        )
}

@Preview(showBackground = true)

@Composable

fun PreviewPermisisonDialog() {

    PermissionDialog(
        permission = CameraPermissionExplainer(),
        isDeniedForever = true,
        onDismiss = { /*TODO*/ },
        onOk = { /*TODO*/ },
        onGoSettings = { /*TODO*/ })
}


