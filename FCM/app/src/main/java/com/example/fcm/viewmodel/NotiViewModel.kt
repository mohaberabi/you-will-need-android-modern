package com.example.fcm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fcm.data.remote.FcmApi
import com.example.fcm.model.AppNoti
import com.example.fcm.model.MessageDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class NotiViewModel : ViewModel() {


    private val api: FcmApi = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(FcmApi::class.java)


    private val _state = MutableStateFlow(NotiState())


    val state: StateFlow<NotiState>
        get() = _state


    fun onSubmitToken() {

        _state.update {
            it.copy(isTypingToken = false)
        }
    }


    fun onMessageChanged(message: String) {
        _state.update {
            it.copy(
                messageText = message
            )
        }
    }

    fun onRemoteTokenChanged(newToken: String) {
        _state.update {
            it.copy(
                remoteToken = newToken
            )
        }
    }


    fun sendMessage(broadcast: Boolean) {
        viewModelScope.launch {
            val messageDto =
                MessageDto(
                    if (broadcast) null else _state.value.remoteToken,
                    AppNoti("TEST TITLE", "TEST BODY")
                )


            try {
                if (broadcast) {
                    api.broadcast(messageDto)
                } else {
                    api.sendMessage(messageDto)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }
}