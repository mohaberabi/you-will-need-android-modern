package com.example.permissionsystem.util

import android.Manifest
import android.graphics.Camera

enum class AppPermissions {


    CAMERA,
    RECORDER,
    PHONE_CALL,

    UNKNOWN
}


fun String.mapToAppPermisssion(): AppPermissions {


    return when (this) {
        Manifest.permission.CAMERA -> AppPermissions.CAMERA
        Manifest.permission.CALL_PHONE -> AppPermissions.PHONE_CALL
        Manifest.permission.RECORD_AUDIO -> AppPermissions.RECORDER
        else -> AppPermissions.UNKNOWN
    }
}


fun AppPermissions.toPermission(): String {
    return when (this) {
        AppPermissions.CAMERA -> Manifest.permission.CAMERA
        AppPermissions.PHONE_CALL -> Manifest.permission.CALL_PHONE
        AppPermissions.RECORDER -> Manifest.permission.RECORD_AUDIO
        else -> ""
    }
}


fun AppPermissions.explain(): PermissionExplainer {
    return when (this) {
        AppPermissions.CAMERA -> CameraPermissionExplainer()
        AppPermissions.RECORDER -> AudioPermissionExplainer()
        AppPermissions.PHONE_CALL -> PhoneCallPermissionExplainer()
        else -> CameraPermissionExplainer()
    }
}