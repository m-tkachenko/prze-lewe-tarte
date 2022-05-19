package app.salo.przelewetarte.di

import app.salo.przelewetarte.data.repository.AuthRepositoryImpl
import app.salo.przelewetarte.domain.repository.AuthRepository
import app.salo.przelewetarte.domain.use_case.UserAuthenticationUseCases
import app.salo.przelewetarte.domain.use_case.user_authentication.UserAlreadyAuthenticatedUseCase
import app.salo.przelewetarte.domain.use_case.user_authentication.UserSignInUseCase
import app.salo.przelewetarte.domain.use_case.user_authentication.UserSignOutUseCase
import app.salo.przelewetarte.domain.use_case.user_authentication.UserSignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesFirebaseAuth() = Firebase.auth

    @Provides
    fun providesAuthRepository(auth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(auth)
    }

    @Provides
    fun providesUseCases(repository: AuthRepository) = UserAuthenticationUseCases(
        signIn = UserSignInUseCase(repository),
        signOut = UserSignOutUseCase(repository),
        signUp = UserSignUpUseCase(repository),
        isUserAuthenticated = UserAlreadyAuthenticatedUseCase(repository)
    )
}