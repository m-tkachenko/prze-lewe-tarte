package app.salo.przelewetarte.presentation.sign_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.use_case.UserMainUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val user: UserMainUseCases
): ViewModel() {
    val isUserAuthenticated get() = user.isUserAuthenticated()

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            user.signIn(email, password).collect { result ->
                when(result) {
                    is Resource.Success -> _state.value = AuthState(isSignIn = true)
                    is Resource.Error -> _state.value = AuthState(error = result.message ?: "Error")
                    is Resource.Loading -> _state.value = AuthState(isLoading = true)
                }
            }
        }
    }
}