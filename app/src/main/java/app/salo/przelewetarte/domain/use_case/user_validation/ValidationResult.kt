package app.salo.przelewetarte.domain.use_case.user_validation

data class ValidationResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null
)
