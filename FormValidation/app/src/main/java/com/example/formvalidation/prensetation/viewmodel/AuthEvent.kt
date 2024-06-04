package com.example.formvalidation.prensetation.viewmodel


sealed class AuthEvent {
    data class OnPasswordChanged(val password: String) : AuthEvent()
    data object OnAuthRequest : AuthEvent()
    data object OnShowPasswordChanged : AuthEvent()
    data object OnShowRepeatedPasswordChanged : AuthEvent()

    data object OnTermsChanged : AuthEvent()
    data class OnRepeatedPasswordChanged(val repeatedPassword: String) : AuthEvent()

    data class OnEmailChanged(val email: String) : AuthEvent()
}