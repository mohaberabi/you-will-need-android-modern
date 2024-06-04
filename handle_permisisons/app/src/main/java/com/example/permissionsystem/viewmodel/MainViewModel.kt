package com.example.permissionsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.permissionsystem.util.AppPermissions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {


    private val _visiblePermissionDialogueQueue = MutableStateFlow(mutableListOf<AppPermissions>())

    val queue: StateFlow<List<AppPermissions>>
        get() = _visiblePermissionDialogueQueue

    fun dismissDialog() {

        _visiblePermissionDialogueQueue.value.removeFirst()
    }

    fun onPermissionResult(
        permission: AppPermissions,
        isGranted: Boolean
    ) {

        if (!isGranted && !_visiblePermissionDialogueQueue.value.contains(permission)) {
            _visiblePermissionDialogueQueue.value.add(permission)
        }
    }


}

