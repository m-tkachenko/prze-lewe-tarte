package app.salo.przelewetarte.domain.use_case.user_main

import app.salo.przelewetarte.domain.repository.MainRepository
import javax.inject.Inject

class UserAlreadyAuthenticatedUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke() =
        repository.isUserAlreadyAuthenticated()
}