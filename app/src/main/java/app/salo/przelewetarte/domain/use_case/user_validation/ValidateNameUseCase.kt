package app.salo.przelewetarte.domain.use_case.user_validation

class ValidateNameUseCase {
    operator fun invoke(username: String): ValidationResult {
        if (username.isBlank())
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The name can't be blank"
            )

        return ValidationResult(
            isSuccess = true
        )
    }
}