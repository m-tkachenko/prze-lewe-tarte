package app.salo.przelewetarte.domain.repository

import android.net.Uri
import app.salo.przelewetarte.common.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    // authentication
    fun isUserAlreadyAuthenticated(): Boolean

    suspend fun userSignIn(email: String, password: String): Flow<Resource<Boolean>>
    suspend fun userSignUp(email: String, password: String, username: String): Flow<Resource<Boolean>>
    suspend fun userSignOut()

    // database
    suspend fun editUsername(username: String): Flow<Resource<Boolean>>
    suspend fun addProfilePhoto(photoUri: Uri): Flow<Resource<Boolean>>
    suspend fun addPhoto(photoUri: Uri): Flow<Resource<Boolean>>
}