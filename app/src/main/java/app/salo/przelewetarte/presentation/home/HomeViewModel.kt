package app.salo.przelewetarte.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.use_case.UserAuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userAuthentication: UserAuthenticationUseCases
): ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    fun signOutUser() {
        viewModelScope.launch {
            userAuthentication.signOut().collect { result ->
                when(result) {
                    is Resource.Success -> _state.value = HomeState(isSignOut = true)
                    is Resource.Error -> _state.value = HomeState(error = result.message ?: "Error")
                    is Resource.Loading -> _state.value = HomeState(isLoading = true)
                }
            }
        }
    }
}