package app.salo.przelewetarte.presentation.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import app.salo.przelewetarte.R
import app.salo.przelewetarte.domain.use_case.UserMainUseCases
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val user: UserMainUseCases
): ViewModel() {
    val photoUri: Uri =
        Uri.fromFile(File(
            "android.mipmap://" +
                    R::class.java.`package`!!.name + "/" +
                    R.drawable.profile_image_3
        ))

    private val storage = Firebase.storage("gs://prze-lewe-tarte-1b203.appspot.com/").reference

    fun createUserFolder() {
        storage
            .child("users")
            .child("007")
            .putFile(photoUri)

            .addOnFailureListener {
                Log.d("PUPPY", it.toString())
            }
    }
}

//    fun signOutUser() {
//        viewModelScope.launch {
//            user.signOut()
//        }
//    }