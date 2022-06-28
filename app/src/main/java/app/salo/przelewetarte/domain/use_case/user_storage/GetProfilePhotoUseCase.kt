package app.salo.przelewetarte.domain.use_case.user_storage

import app.salo.przelewetarte.domain.repository.StorageRepository
import javax.inject.Inject

class GetProfilePhotoUseCase @Inject constructor(
    val repository: StorageRepository
) {
}