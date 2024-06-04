package com.example.formvalidation.prensetation.use_case

import com.example.formvalidation.util.ValidateResult

class ValidateRepeatedPasswordUseCase {


    operator fun invoke(password: String, repeatedPassword: String): ValidateResult {


        val isEqual = password == repeatedPassword

        return ValidateResult(isEqual, if (isEqual) null else "Passwords does not match ")
    }
}