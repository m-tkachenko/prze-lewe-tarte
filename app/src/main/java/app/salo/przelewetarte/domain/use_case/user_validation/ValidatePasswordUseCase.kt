package app.salo.przelewetarte.domain.use_case.user_validation

class ValidatePasswordUseCase {
    operator fun invoke(password: String): ValidationResult {
        if (password.length < 8)
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The password is blank or to short"
            )

        return ValidationResult(
            isSuccess = true
        )
    }
}