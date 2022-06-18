package app.salo.przelewetarte.domain.use_case.user_validation

import android.util.Patterns

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank())
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The email can't be blank"
            )

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The email is not validate"
            )

        return ValidationResult(
            isSuccess = true
        )
    }
}