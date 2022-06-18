package app.salo.przelewetarte.domain.use_case.user_validation

class ValidateTermsUseCase {
    operator fun invoke(isTermsAccepted: Boolean): ValidationResult {
        if (!isTermsAccepted)
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Please accept our terms"
            )

        return ValidationResult(
            isSuccess = true
        )
    }
}