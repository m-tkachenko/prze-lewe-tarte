package app.salo.przelewetarte.domain.use_case.user_authentication

import app.salo.przelewetarte.domain.repository.AuthRepository
import javax.inject.Inject

class UserSignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        repository.userSignIn(email, password)
}