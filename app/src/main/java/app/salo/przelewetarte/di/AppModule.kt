package app.salo.przelewetarte.di

import app.salo.przelewetarte.data.repository.DatabaseRepositoryImpl
import app.salo.przelewetarte.data.repository.MainRepositoryImpl
import app.salo.przelewetarte.domain.repository.DatabaseRepository
import app.salo.przelewetarte.domain.repository.MainRepository
import app.salo.przelewetarte.domain.use_case.UserMainUseCases
import app.salo.przelewetarte.domain.use_case.user_main.UserAlreadyAuthenticatedUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignInUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignOutUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesFirebaseAuth() = Firebase.auth
    @Provides
    fun providesDatabaseReference() = FirebaseDatabase
        .getInstance("https://prze-lewe-tarte-1b203-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference

    @Provides
    fun providesMainRepository(databaseReference: DatabaseReference, auth: FirebaseAuth): MainRepository {
        return MainRepositoryImpl(databaseReference, auth)
    }
    @Provides
    fun providesDatabaseRepository(databaseReference: DatabaseReference, auth: FirebaseAuth): DatabaseRepository {
        return DatabaseRepositoryImpl(databaseReference, auth)
    }

    @Provides
    fun providesAuthUseCases(mainRepository: MainRepository) = UserMainUseCases(
        signIn = UserSignInUseCase(mainRepository),
        signOut = UserSignOutUseCase(mainRepository),
        signUp = UserSignUpUseCase(mainRepository),
        isUserAuthenticated = UserAlreadyAuthenticatedUseCase(mainRepository)
    )
}