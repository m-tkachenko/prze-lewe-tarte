package app.salo.przelewetarte.domain.use_case.user_authentication

import app.salo.przelewetarte.domain.repository.AuthRepository
import javax.inject.Inject

class UserAlreadyAuthenticatedUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.isUserAlreadyAuthenticated()
}