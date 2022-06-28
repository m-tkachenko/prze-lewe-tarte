package app.salo.przelewetarte.domain.repository

import android.net.Uri
import app.salo.przelewetarte.common.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    // authentication
    fun isUserAlreadyAuthenticated(): Boolean
    fun getUserUid(): String

    suspend fun getUsername(): Flow<Resource<String>>
    suspend fun userSignIn(email: String, password: String): Flow<Resource<Boolean>>
    suspend fun userSignUp(email: String, password: String, username: String): Flow<Resource<Boolean>>
    suspend fun userSignOut()

    // database
    suspend fun editUsername(username: String): Flow<Resource<Boolean>>
}