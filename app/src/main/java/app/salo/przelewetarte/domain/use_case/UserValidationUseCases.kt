package app.salo.przelewetarte.domain.use_case

import app.salo.przelewetarte.domain.use_case.user_validation.ValidateEmailUseCase
import app.salo.przelewetarte.domain.use_case.user_validation.ValidateNameUseCase
import app.salo.przelewetarte.domain.use_case.user_validation.ValidatePasswordUseCase
import app.salo.przelewetarte.domain.use_case.user_validation.ValidateTermsUseCase

data class UserValidationUseCases(
    val email: ValidateEmailUseCase,
    val password: ValidatePasswordUseCase,
    val terms: ValidateTermsUseCase,
    val username: ValidateNameUseCase
)
