package app.salo.przelewetarte.presentation.camera

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.use_case.UserStorageUseCases
import app.salo.przelewetarte.presentation.camera.states.CameraState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val storage: UserStorageUseCases
): ViewModel() {
    private val _addPhotoState = mutableStateOf(CameraState())
    val addPhotoState: State<CameraState> = _addPhotoState

    fun addPhoto(photoUri: Uri) {
        viewModelScope.launch {
            storage.addPhoto(photoUri).collect { result ->
                when(result) {
                    is Resource.Success ->
                        _addPhotoState.value =
                            CameraState(isSuccess = true)
                    is Resource.Error ->
                        _addPhotoState.value =
                            CameraState(isSuccess = false, error = result.message ?: "Smth went wrong")
                    is Resource.Loading ->
                        _addPhotoState.value =
                            CameraState(isLoading = true)
                }
            }
        }
    }
}