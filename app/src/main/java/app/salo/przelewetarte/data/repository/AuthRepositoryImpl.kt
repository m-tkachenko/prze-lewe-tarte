package app.salo.przelewetarte.data.repository

import android.util.Log
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val database: DatabaseReference,
    private val auth: FirebaseAuth
): AuthRepository {
    override fun isUserAlreadyAuthenticated(): Boolean = auth.currentUser != null
    override fun getUserUid(): String = auth.uid ?: "007"

    override suspend fun userSignIn(email: String, password: String): Flow<Resource<Boolean>> {
        val signInResult = MutableStateFlow<Resource<Boolean>>(Resource.Loading())

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                Log.d("UserSignIn", "IS SUCCESS")
                signInResult.value =
                    Resource.Success(
                        data = true
                    )
            }
            .addOnFailureListener { e ->
                Log.e("UserSignIn", "IS ERROR: ${e.message}")
                signInResult.value =
                    Resource.Error(
                        data = false,
                        message = e.message ?: "Smth went wrong"
                    )
            }

        return signInResult
    }

    override suspend fun userSignUp(email: String, password: String, username: String): Flow<Resource<Boolean>> {
        val signUpResult = MutableStateFlow<Resource<Boolean>>(Resource.Loading())

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                // add user to database
                database
                    .child("users")
                    .child(getUserUid())
                    .child("username")
                    .setValue(username)

                    .addOnCompleteListener {
                        if (auth.uid != null) {
                            Log.d("CreateUser", "SUCCESS")
                            signUpResult.value =
                                Resource.Success(
                                    data = true
                                )
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e("CreateUser", "IS ERROR: ${e.message}")
                        signUpResult.value =
                            Resource.Error(
                                data = false,
                                message = e.message ?: "Smth went wrong"
                            )
                    }
            }
            .addOnFailureListener { e ->
                Log.e("UserSignUp", "IS ERROR: ${e.message}")
                signUpResult.value =
                    Resource.Error(
                        data = false,
                        message = e.message ?: "Smth went wrong"
                    )
            }

        return signUpResult
    }

    override suspend fun userSignOut() = auth.signOut()

    override suspend fun editUsername(username: String): Flow<Resource<Boolean>> {
        val editUsernameResult = MutableStateFlow<Resource<Boolean>>(Resource.Loading())

        database
            .child("users")
            .child(getUserUid())
            .child("username")
            .setValue(username)

            .addOnCompleteListener {
                Log.d("EditUsername", "SUCCESS")
                editUsernameResult.value =
                    Resource.Success(
                        data = true
                    )
            }
            .addOnFailureListener { e ->
                Log.e("EditUsername", "IS ERROR: ${e.message}")
                editUsernameResult.value =
                    Resource.Error(
                        data = false,
                        message = e.message ?: "Smth went wrong"
                    )
            }

        return editUsernameResult
    }

    override suspend fun getUsername(): Flow<Resource<String>> {
        val getUsernameResult = MutableStateFlow<Resource<String>>(Resource.Loading())

        database
            .child("users")
            .child(getUserUid())
            .child("username")
            .get()

            .addOnCompleteListener { task ->
                Log.d("GetUsername", "SUCCESS")
                getUsernameResult.value =
                    Resource.Success(
                        data = task.result.value.toString()
                    )
            }
            .addOnFailureListener { e ->
                Log.e("GetUsername", "IS ERROR: ${e.message}")
                getUsernameResult.value =
                    Resource.Error(
                        message = e.message ?: "Smth went wrong"
                    )
            }

        return getUsernameResult
    }
}