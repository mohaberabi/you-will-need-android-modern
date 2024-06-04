package com.example.formvalidation.prensetation.viewmodel


data class AuthState(
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatedPasswordError: String? = null,
    val acceptTerms: Boolean = false,
    val termsError: String? = null,
    val showPassword: Boolean = false,
    val showRepeatedPassword: Boolean = false,
)
