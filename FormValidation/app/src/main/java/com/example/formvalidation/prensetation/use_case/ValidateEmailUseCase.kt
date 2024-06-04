package com.example.formvalidation.prensetation.use_case

import android.util.Patterns
import com.example.formvalidation.util.ValidateResult


class ValidateEmailUseCase() {


    operator fun invoke(email: String): ValidateResult {
        if (email.isBlank()) {
            return ValidateResult(false, "Email can not be empty")
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidateResult(false, "Please Type A Correct Email Address")

        }
        return ValidateResult(true)

    }
}