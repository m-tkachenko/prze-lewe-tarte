package app.salo.przelewetarte.domain.use_case.common

import app.salo.przelewetarte.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserUidUseCase @Inject constructor(
    val repository: AuthRepository
) {
    operator fun invoke() =
        repository.getUserUid()
}