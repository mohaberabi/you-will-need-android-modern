package com.example.permissionsystem

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.permissionsystem.compose.PermissionDialog
import com.example.permissionsystem.ui.theme.PermissionSystemTheme
import com.example.permissionsystem.util.AppPermissions
import com.example.permissionsystem.util.explain
import com.example.permissionsystem.util.mapToAppPermisssion
import com.example.permissionsystem.util.openAppSettings
import com.example.permissionsystem.util.toPermission
import com.example.permissionsystem.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PermissionSystemTheme {
                val viewModel = viewModel<MainViewModel>()

                val queue = viewModel.queue.collectAsState().value

                val cameraPerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        viewModel.onPermissionResult(
                            AppPermissions.CAMERA,
                            isGranted
                        )
                    }
                )
                val multiplePermissions = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions(),
                    onResult = { perMap ->

                        perMap.keys.forEach { permission ->
                            viewModel.onPermissionResult(
                                permission = permission.mapToAppPermisssion(),
                                isGranted = perMap[permission] == true
                            )
                        }

                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Button(
                        onClick = {
                            cameraPerLauncher.launch(AppPermissions.CAMERA.toPermission())
                        },
                    ) {

                        Text(text = "Request one permission")
                    }

                    Button(
                        onClick = {
                            multiplePermissions.launch(
                                arrayOf(
                                    AppPermissions.PHONE_CALL.toPermission(),
                                    AppPermissions.CAMERA.toPermission(),
                                    AppPermissions.RECORDER.toPermission(),
                                )
                            )
                        },

                        ) {

                        Text(text = "Request multiple permissions")
                    }

                    queue.forEach { per ->
                        PermissionDialog(
                            permission = per.explain(),
                            isDeniedForever = !shouldShowRequestPermissionRationale(per.toPermission()),
                            onDismiss = viewModel::dismissDialog,
                            onOk = {
                                viewModel.dismissDialog()
                                multiplePermissions.launch(arrayOf(per.toPermission()))
                            },
                            onGoSettings = {

                                openAppSettings()
                            },
                        )
                    }
                }
            }
        }
    }
}

