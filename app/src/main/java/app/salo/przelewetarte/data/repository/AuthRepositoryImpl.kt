package app.salo.przelewetarte.data.repository

import android.util.Log
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    private val TAG = "Authenticate"

    override fun isUserAlreadyAuthenticated(): Boolean = auth.currentUser != null

    override suspend fun userSignIn(email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            auth.signInWithEmailAndPassword(email, password)

            Log.d(TAG, "sign in user using email and password is: Success")
            emit(Resource.Success(true))
        } catch (e: Exception) {
            Log.d(TAG, "sign in user using email and password is: Failed: ${e.message}")
            emit(Resource.Error(false, e.message ?: "Smth went wrong"))
        } catch (e: IOException) {
            Log.d(TAG, "sign in user using email and password is: Failed: Internet connection")
            emit(Resource.Error(false, "Check your internet connection"))
        }
    }

    override suspend fun userSignUp(): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun userSignOut() = auth.signOut()

    override fun getFirebaseAuthState(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}