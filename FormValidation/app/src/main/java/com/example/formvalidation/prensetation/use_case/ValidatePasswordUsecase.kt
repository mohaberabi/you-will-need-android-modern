package com.example.formvalidation.prensetation.use_case

import com.example.formvalidation.util.ValidateResult

class ValidatePasswordUsecase {


    operator fun invoke(password: String): ValidateResult {

        if (password.isBlank()) {

            return ValidateResult(
                false,
                "Password can not be empty"
            )
        }


        if (password.length < 8) {
            return ValidateResult(
                false,
                "Password can not be less than 8 characters"
            )

        }

        val hasDigit = password.any {
            it.isDigit()
        }

        if (!hasDigit) {
            return ValidateResult(
                false,
                "Password must contain at least one digit"
            )

        }


        val hasUpperCase = password.any {
            it.isUpperCase()
        }
        if (!hasUpperCase) {
            return ValidateResult(
                false,
                "Password must contain at least one upper case character"
            )
        }
        val hasSpecialCharacter = "~!@#$%^&*()_+}{.<?>".any {
            it in password
        }
        if (!hasSpecialCharacter) {
            return ValidateResult(
                false,
                "Password must contain at least one special  character "
            )
        }

        return ValidateResult(true)
    }
}