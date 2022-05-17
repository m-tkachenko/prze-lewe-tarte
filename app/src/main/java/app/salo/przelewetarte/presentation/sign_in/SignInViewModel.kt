package app.salo.przelewetarte.presentation.sign_in

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
class SignInViewModel @Inject constructor(
    private val userAuthentication: UserAuthenticationUseCases
): ViewModel() {
    val isUserAuthenticated get() = userAuthentication.isUserAuthenticated()

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            userAuthentication.signIn(email, password).collect { result ->
                when(result) {
                    is Resource.Success -> _state.value = SignInState(isSignIn = true)
                    is Resource.Error -> _state.value = SignInState(error = result.message ?: "Error")
                    is Resource.Loading -> _state.value = SignInState(isLoading = true)
                }
            }
        }
    }
}