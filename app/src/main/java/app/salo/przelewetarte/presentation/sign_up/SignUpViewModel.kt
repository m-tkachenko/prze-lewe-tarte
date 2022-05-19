package app.salo.przelewetarte.presentation.sign_up

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
class SignUpViewModel @Inject constructor(
    private val userAuthentication: UserAuthenticationUseCases
): ViewModel() {
    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    fun signUpUser(email: String, password: String) {
        viewModelScope.launch {
            userAuthentication.signUp(email, password).collect { result ->
                when(result) {
                    is Resource.Success -> _state.value = SignUpState(isSignUp = true)
                    is Resource.Error -> _state.value = SignUpState(error = result.message ?: "Error")
                    is Resource.Loading -> _state.value = SignUpState(isLoading = true)
                }
            }
        }
    }
}