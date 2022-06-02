package app.salo.przelewetarte.data.repository

import android.util.Log
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.repository.MainRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val database: DatabaseReference,
    private val auth: FirebaseAuth
): MainRepository {

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

    override suspend fun userSignUp(email: String, password: String, username: String): Flow<Resource<Boolean>> {
        val signUpResult = MutableStateFlow<Resource<Boolean>>(Resource.Loading())

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                database
                    .child("users")
                    .child(auth.uid ?: "333")
                    .child("username")
                    .setValue(username)

                    .addOnCompleteListener {
                        Log.d("CreateUser", "SUCCESS")
                        signUpResult.value = Resource.Success(true)
                    }
                    .addOnFailureListener { e ->
                        Log.e("CreateUser", "IS ERROR: ${e.message}")
                        signUpResult.value = Resource.Error(false, e.message ?: "Smth went wrong")
                    }
            }
            .addOnFailureListener { e ->
                Log.d("UserSignUp", "IS ERROR: ${e.message}")
                signUpResult.value = Resource.Error(true, e.message ?: "Smth went wrong")
            }

        Log.d("PUPPY", (signUpResult.value.data ?: "aaa") as String)

        return signUpResult
    }

    override suspend fun userSignOut() =
        auth.signOut()

    override suspend fun editUsername(username: String): Flow<Resource<Boolean>> {
        val editUsernameResult = MutableStateFlow<Resource<Boolean>>(Resource.Loading())

        database
            .child("users")
            .child(auth.uid ?: "777")
            .child("username")
            .setValue(username)

            .addOnCompleteListener {
                Log.d("CreateUser", "SUCCESS")
                editUsernameResult.value = Resource.Success(true)
            }
            .addOnFailureListener { e ->
                Log.e("CreateUser", "IS ERROR: ${e.message}")
                editUsernameResult.value = Resource.Error(false, e.message ?: "Smth went wrong")
            }

        return editUsernameResult
    }

    override suspend fun addPhoto(): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}