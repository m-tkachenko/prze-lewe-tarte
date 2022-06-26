package app.salo.przelewetarte.domain.use_case

import app.salo.przelewetarte.domain.use_case.user_storage.*

data class UserStorageUseCases(
    val addPhoto: AddPhotoUseCase,
    val addProfilePhtoto: AddProfilePhotoUseCase,
    val getPhotos: GetPhotosUseCase,
    val getProfilePhoto: GetProfilePhotoUseCase,
)
