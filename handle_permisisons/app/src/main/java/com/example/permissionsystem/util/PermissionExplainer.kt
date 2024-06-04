package com.example.permissionsystem.util

import androidx.compose.runtime.Composable

interface PermissionExplainer {


    fun getString(isDeniedForever: Boolean): UiText

}


class CameraPermissionExplainer : PermissionExplainer {
    override fun getString(isDeniedForever: Boolean): UiText {
        return if (isDeniedForever) {
            UiText.DynamicText("It Looks Like that you disbaled the camera , you can open app settings to enable")
        } else {
            UiText.DynamicText("we need to allow camera to take photos ")

        }
    }

}

class AudioPermissionExplainer : PermissionExplainer {
    override fun getString(isDeniedForever: Boolean): UiText {
        return if (isDeniedForever) {
            UiText.DynamicText("It Looks Like that you disabled the audio , you can open app settings to enable ")
        } else {
            UiText.DynamicText("We need your  recorder permission to record ")

        }
    }

}

class PhoneCallPermissionExplainer : PermissionExplainer {
    override fun getString(isDeniedForever: Boolean): UiText {
        return if (isDeniedForever) {
            UiText.DynamicText("It Looks Like that you disabled the phone call kit  , you can open app settings to enable ")
        } else {
            UiText.DynamicText("We need your  phone call kit  permission to make an internet call ")

        }
    }

}