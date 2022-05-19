package app.salo.przelewetarte.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override fun isUserAlreadyAuthenticated(): Boolean = auth.currentUser != null

    override suspend fun userSignIn(email: String, password: String): Flow<Resource<Boolean>> {
        val signInResult = MutableStateFlow<Resource<Boolean>>(Resource.Loading())

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) return@addOnCompleteListener

                Log.d("UserSignIn", "IS SUCCESS")
                signInResult.value = Resource.Success(true)
            }
            .addOnFailureListener { e ->
                Log.d("UserSignIn", "IS ERROR: ${e.message}")
                signInResult.value = Resource.Error(false, e.message ?: "Smth went wrong")
            }

        return signInResult
    }

    override suspend fun userSignUp(email: String, password: String): Flow<Resource<Boolean>> {
        val signUpResult = MutableStateFlow<Resource<Boolean>>(Resource.Loading())

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) return@addOnCompleteListener

                Log.d("UserSignUp", "IS SUCCESS")
                signUpResult.value = Resource.Success(true)
            }
            .addOnFailureListener { e ->
                Log.d("UserSignUp", "IS ERROR: ${e.message}")
                signUpResult.value = Resource.Error(false, e.message ?: "Smth went wrong")
            }

        return signUpResult
    }

    override suspend fun userSignOut() =
        auth.signOut()

    override fun getFirebaseAuthState(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}