package app.salo.przelewetarte.domain.use_case.user_authentication

import app.salo.przelewetarte.domain.repository.AuthRepository
import javax.inject.Inject

class UserSignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        repository.userSignUp(email, password)
}