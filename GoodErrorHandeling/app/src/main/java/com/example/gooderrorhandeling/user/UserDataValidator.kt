package com.example.gooderrorhandeling.user

import com.example.gooderrorhandeling.util.app_error.AppError
import com.example.gooderrorhandeling.util.AppResult
import com.example.gooderrorhandeling.util.app_error.PasswordError

class UserDataValidator {


    fun validatePassword(
        password: String
    ): AppResult<Unit, PasswordError> {

        if (password.length < 9) {
            return AppResult.Error(PasswordError.TOO_SHORT)
        }

        if (password.any {
                !it.isUpperCase()
            }
        ) {
            return AppResult.Error(PasswordError.NO_UPPERCASE)
        }

        if (password.any { !it.isDigit() }) {
            return AppResult.Error(PasswordError.NO_DIGIT)

        }

        return AppResult.Done(Unit)
    }


}

