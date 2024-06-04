package com.example.gooderrorhandeling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gooderrorhandeling.user.UserDataValidator
import com.example.gooderrorhandeling.user.UserRepository
import com.example.gooderrorhandeling.util.AppResult
import com.example.gooderrorhandeling.util.app_error.DataError
import com.example.gooderrorhandeling.util.app_error.PasswordError
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val userRepository: UserRepository,

    ) : ViewModel() {


    fun getUser() {
        viewModelScope.launch {
            when (val res = userRepository.login("123456789")) {

                is AppResult.Error -> {
                    when (res.error) {

                        DataError.NetWork.SERIALIZATION_ERROR -> {

                        }
                        // add all the remaining errors
                        else -> {}
                    }
                }

                is AppResult.Done -> {
                    println(res.data.toString())
                }
            }

        }
    }

    fun validatePassword(password: String) {


        when (val res = userDataValidator.validatePassword(password)) {
            is AppResult.Error -> {

                when (res.error) {
                    PasswordError.NO_UPPERCASE -> {
                        println("")
                    }

                    PasswordError.NO_DIGIT -> {}
                    PasswordError.TOO_SHORT -> {}

                }
            }

            is AppResult.Done -> {

            }
        }
    }
}