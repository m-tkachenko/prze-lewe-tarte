package app.salo.przelewetarte.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.use_case.UserAuthUseCases
import app.salo.przelewetarte.domain.use_case.UserStorageUseCases
import app.salo.przelewetarte.presentation.home.states.GetUsernameState
import app.salo.przelewetarte.presentation.profile.states.GetPhotosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val user: UserAuthUseCases,
    private val storage: UserStorageUseCases
): ViewModel() {
    private val _getPhotosState = mutableStateOf(GetPhotosState())
    val getPhotosState: State<GetPhotosState> = _getPhotosState

    private val _getUsernameState = mutableStateOf(GetUsernameState())
    val getUsernameState: State<GetUsernameState> = _getUsernameState

    fun getPhotos() {
        viewModelScope.launch {
            storage.getPhotos().collect { result ->
                when (result) {
                    is Resource.Success ->
                        _getPhotosState.value =
                            GetPhotosState(photos = result.data ?: emptyList())
                    is Resource.Error ->
                        _getPhotosState.value =
                            GetPhotosState(error = result.message ?: "Error")
                    is Resource.Loading ->
                        _getPhotosState.value =
                            GetPhotosState(isLoading = true)
                }
            }
        }
    }

    fun getUsername() {
        viewModelScope.launch {
            user.username().collect { result ->
                when (result) {
                    is Resource.Success ->
                        _getUsernameState.value =
                            GetUsernameState(username = result.data ?: "Rakamakafo")
                    is Resource.Error ->
                        _getUsernameState.value =
                            GetUsernameState(error = result.message ?: "Error")
                    is Resource.Loading ->
                        _getUsernameState.value =
                            GetUsernameState(isLoading = true)
                }
            }
        }
    }

    fun signOutUser() {
        viewModelScope.launch {
            user.signOut()
        }
    }
}

