package com.example.formvalidation.prensetation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.formvalidation.prensetation.use_case.AcceptTermsUsecase
import com.example.formvalidation.prensetation.use_case.ValidateEmailUseCase
import com.example.formvalidation.prensetation.use_case.ValidatePasswordUsecase
import com.example.formvalidation.prensetation.use_case.ValidateRepeatedPasswordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(

    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUsecase: ValidatePasswordUsecase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val acceptTermsUsecase: AcceptTermsUsecase,

    ) : ViewModel() {


    private val _event = Channel<SubmissionEvent>()


    val event = _event.receiveAsFlow()

    private val _state = MutableStateFlow(AuthState())


    val state: StateFlow<AuthState>
        get() = _state

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(email = event.email)
                }
            }

            is AuthEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(password = event.password)
                }
            }

            is AuthEvent.OnRepeatedPasswordChanged -> {
                _state.update {
                    it.copy(repeatedPassword = event.repeatedPassword)
                }
            }

            is AuthEvent.OnTermsChanged -> {
                _state.update {
                    it.copy(acceptTerms = !it.acceptTerms)
                }
            }

            is AuthEvent.OnShowRepeatedPasswordChanged -> {
                _state.update {
                    it.copy(showRepeatedPassword = !it.showRepeatedPassword)
                }
            }

            is AuthEvent.OnShowPasswordChanged -> {
                _state.update {
                    it.copy(showPassword = !it.showPassword)
                }
            }


            is AuthEvent.OnAuthRequest -> submit()

        }
    }


    fun submit() {

        val emailError = validateEmailUseCase(_state.value.email)
        val passwordError = validatePasswordUsecase(_state.value.password)
        val repeatedPasswordError =
            validateRepeatedPasswordUseCase(
                _state.value.password,
                _state.value.repeatedPassword
            )
        val termsError = acceptTermsUsecase(_state.value.acceptTerms)


        val hasError = listOf(
            emailError,
            passwordError,
            repeatedPasswordError,
            termsError,
        ).any {
            !it.done
        }

        if (hasError) {
            _state.update {
                it.copy(
                    emailError = emailError.reason,
                    passwordError = passwordError.reason,
                    termsError = termsError.reason,
                    repeatedPasswordError = repeatedPasswordError.reason,
                )
            }
            return
        }

        viewModelScope.launch {
            _event.send(SubmissionEvent.Done)
        }

    }
}

sealed class SubmissionEvent {
    data object Done : SubmissionEvent()

}

class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(
                ValidateEmailUseCase(),
                ValidatePasswordUsecase(),
                ValidateRepeatedPasswordUseCase(),
                AcceptTermsUsecase()
            ) as T
        }
        throw IllegalArgumentException("ERROR ")
    }
}