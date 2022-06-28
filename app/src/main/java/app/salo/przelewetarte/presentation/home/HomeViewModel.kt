package app.salo.przelewetarte.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.salo.przelewetarte.common.Materials
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.use_case.UserAuthUseCases
import app.salo.przelewetarte.presentation.home.states.GetUsernameState
import app.salo.przelewetarte.presentation.profile.states.GetPhotosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val user: UserAuthUseCases
): ViewModel() {
    private val materials = Materials()

    private val _getUsernameState = mutableStateOf(GetUsernameState())
    val getUsernameState: State<GetUsernameState> = _getUsernameState

    val lessonQuantity = materials.images.size
    val images = materials.images.toList()
    val descroptionTestIds = materials.descriptions.toList()

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
}