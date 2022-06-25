package app.salo.przelewetarte.presentation.camera

import androidx.lifecycle.ViewModel
import app.salo.przelewetarte.domain.use_case.UserMainUseCases
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val user: UserMainUseCases
): ViewModel() {
    private val storage =
        Firebase.storage("gs://prze-lewe-tarte-1b203.appspot.com/").reference


}