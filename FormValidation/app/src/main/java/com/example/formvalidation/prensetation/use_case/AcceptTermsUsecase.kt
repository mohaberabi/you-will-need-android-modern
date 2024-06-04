package com.example.formvalidation.prensetation.use_case

import com.example.formvalidation.util.ValidateResult

class AcceptTermsUsecase {


    operator fun invoke(accept: Boolean): ValidateResult {

        return ValidateResult(
            accept,
            if (accept) null else "You must accept our terms and conditions to use the dumy app "
        )
    }
}