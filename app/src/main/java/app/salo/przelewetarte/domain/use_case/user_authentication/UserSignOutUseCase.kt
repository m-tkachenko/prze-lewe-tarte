package app.salo.przelewetarte.domain.use_case.user_authentication

import android.util.Log
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UserSignOutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() =
        repository.userSignOut()
}