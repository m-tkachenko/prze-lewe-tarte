package app.salo.przelewetarte.di

import app.salo.przelewetarte.data.repository.StorageRepositoryImpl
import app.salo.przelewetarte.data.repository.AuthRepositoryImpl
import app.salo.przelewetarte.domain.repository.StorageRepository
import app.salo.przelewetarte.domain.repository.AuthRepository
import app.salo.przelewetarte.domain.use_case.UserAuthUseCases
import app.salo.przelewetarte.domain.use_case.UserStorageUseCases
import app.salo.przelewetarte.domain.use_case.UserValidationUseCases
import app.salo.przelewetarte.domain.use_case.user_main.UserAlreadyAuthenticatedUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignInUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignOutUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignUpUseCase
import app.salo.przelewetarte.domain.use_case.user_storage.AddPhotoUseCase
import app.salo.przelewetarte.domain.use_case.user_storage.AddProfilePhotoUseCase
import app.salo.przelewetarte.domain.use_case.user_storage.GetPhotosUseCase
import app.salo.przelewetarte.domain.use_case.user_storage.GetProfilePhotoUseCase
import app.salo.przelewetarte.domain.use_case.user_validation.ValidateEmailUseCase
import app.salo.przelewetarte.domain.use_case.user_validation.ValidateNameUseCase
import app.salo.przelewetarte.domain.use_case.user_validation.ValidatePasswordUseCase
import app.salo.przelewetarte.domain.use_case.user_validation.ValidateTermsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Firebase stuff
    @Provides
    fun providesFirebaseAuth() = Firebase.auth
    @Provides
    fun providesDatabaseReference() = FirebaseDatabase
        .getInstance("https://prze-lewe-tarte-1b203-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference
    @Provides
    fun providesStorageReference() = Firebase
        .storage("gs://prze-lewe-tarte-1b203.appspot.com/")
        .reference

    // Repository stuff
    @Provides
    fun providesAuthRepository(databaseReference: DatabaseReference, auth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(databaseReference, auth)
    }
    @Provides
    fun providesStorageRepository(storage: StorageReference, auth: FirebaseAuth): StorageRepository {
        return StorageRepositoryImpl(storage, auth)
    }

    // UseCases stuff
    @Provides
    fun providesAuthUseCases(authRepository: AuthRepository) = UserAuthUseCases(
        signIn = UserSignInUseCase(authRepository),
        signOut = UserSignOutUseCase(authRepository),
        signUp = UserSignUpUseCase(authRepository),
        isUserAuthenticated = UserAlreadyAuthenticatedUseCase(authRepository)
    )

    @Provides
    fun providesStorageUseCases(storageRepository: StorageRepository) = UserStorageUseCases(
        addPhoto = AddPhotoUseCase(storageRepository),
        addProfilePhtoto = AddProfilePhotoUseCase(storageRepository),
        getPhotos = GetPhotosUseCase(storageRepository),
        getProfilePhoto = GetProfilePhotoUseCase(storageRepository)
    )

    @Provides
    fun providesValidationUseCases() = UserValidationUseCases(
        email = ValidateEmailUseCase(),
        password = ValidatePasswordUseCase(),
        terms = ValidateTermsUseCase(),
        username = ValidateNameUseCase()
    )
}