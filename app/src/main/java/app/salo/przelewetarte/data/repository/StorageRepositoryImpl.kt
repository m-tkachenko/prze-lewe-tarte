package app.salo.przelewetarte.data.repository

import android.net.Uri
import android.util.Log
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.repository.StorageRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storage: StorageReference,
    private val auth: FirebaseAuth
): StorageRepository {
    private fun getUserUid(): String = auth.uid ?: "007"

    override suspend fun addProfilePhoto(photoUri: Uri): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun addPhoto(photoUri: Uri): Flow<Resource<Boolean>> {
        val addPhotoResult = MutableStateFlow<Resource<Boolean>>(Resource.Loading())

        storage
            .child("users")
            .child(getUserUid())
            .child(UUID
                .randomUUID()
                .toString()
                .replace(
                    oldChar = 'f',
                    newChar = '7'
                )
                .plus(
                    other = ".jpg"
                )
            )
            .putFile(photoUri)

            .addOnCompleteListener { task ->
                Log.d("AddPhoto", "SUCCESS")
                addPhotoResult.value =
                    Resource.Success(
                        data = task.isSuccessful
                    )
            }
            .addOnFailureListener { e ->
                Log.e("AddPhoto", "ERROR: ${e.message}")
                addPhotoResult.value =
                    Resource.Error(
                        data = false,
                        message = e.message ?: "Smth went wrong"
                    )
            }

        return addPhotoResult
    }

    override suspend fun getPhotos(): Flow<Resource<List<Uri>>> {
        val urisList = mutableListOf<Uri>()
        val getPhotosResult = MutableStateFlow<Resource<List<Uri>>>(Resource.Loading())

        storage
            .child("users")
            .child(getUserUid())
            .listAll()

            .addOnCompleteListener { task ->
                task
                    .addOnSuccessListener { list ->
                        list.items.forEach { item ->
                            item.downloadUrl
                                .addOnSuccessListener { uri ->
                                    urisList.add(uri)

                                    Log.d("GetPhotos", "SUCCESS")
                                    getPhotosResult.value = Resource.Success(
                                        data = urisList.toList()
                                    )
                                }
                                .addOnFailureListener { e ->
                                    Log.e("GetPhotos", "ERROR")
                                    getPhotosResult.value = Resource.Error(
                                        data = listOf(),
                                        message = e.message
                                    )
                                }
                            }
                    }
                    .addOnFailureListener { e ->
                        Log.e("GetPhotos", "ERROR")
                        getPhotosResult.value = Resource.Error(
                            data = listOf(),
                            message = e.message
                        )
                    }
            }
            .addOnFailureListener { e ->
                Log.e("GetPhotos", "ERROR")
                getPhotosResult.value = Resource.Error(
                    data = listOf(),
                    message = e.message
                )
            }

        return getPhotosResult
    }

    override suspend fun getProfilePhoto(): Flow<Resource<Uri>> {
        TODO("Not yet implemented")
    }

}