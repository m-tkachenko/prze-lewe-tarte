package app.salo.przelewetarte.domain.repository

import android.net.Uri
import app.salo.przelewetarte.common.Resource
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    suspend fun addProfilePhoto(photoUri: Uri): Flow<Resource<Boolean>>
    suspend fun addPhoto(photoUri: Uri): Flow<Resource<Boolean>>

    suspend fun getPhotos(): Flow<Resource<List<Uri>>>
    suspend fun getProfilePhoto(): Flow<Resource<Uri>>
}