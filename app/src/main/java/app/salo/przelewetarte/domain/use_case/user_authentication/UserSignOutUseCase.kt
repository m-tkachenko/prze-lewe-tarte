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
    suspend operator fun invoke() : Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            repository.userSignOut()

            Log.d("UserSignOut", "sign out user is: Success")
            emit(Resource.Success(true))
        } catch (e: Exception) {
            Log.d("UserSignOut", "sign out user is: Failed: ${e.message}")
            emit(Resource.Error(false, e.message ?: "Smth went wrong"))
        } catch (e: IOException) {
            Log.d("UserSignOut", "sign out user is: Failed: Internet connection")
            emit(Resource.Error(false, "Check your internet connection"))
        }
    }
}