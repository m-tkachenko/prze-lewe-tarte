package app.salo.przelewetarte.domain.repository

import app.salo.przelewetarte.common.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserAlreadyAuthenticated(): Boolean

    suspend fun userSignIn(email: String, password: String) : Flow<Resource<Boolean>>
    suspend fun userSignUp() : Flow<Resource<Boolean>>
    suspend fun userSignOut()

    fun getFirebaseAuthState(): Flow<Boolean>
}