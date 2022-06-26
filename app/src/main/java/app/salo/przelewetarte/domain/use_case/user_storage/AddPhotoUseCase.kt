package app.salo.przelewetarte.domain.use_case.user_storage

import android.net.Uri
import app.salo.przelewetarte.domain.repository.StorageRepository
import javax.inject.Inject

class AddPhotoUseCase @Inject constructor(
    val repository: StorageRepository
) {
    suspend operator fun invoke(photoUri: Uri) =
        repository.addPhoto(photoUri)
}