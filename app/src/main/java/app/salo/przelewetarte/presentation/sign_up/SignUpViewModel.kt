package app.salo.przelewetarte.presentation.sign_up

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
class SignUpViewModel @Inject constructor(
    private val user: UserMainUseCases,
): ViewModel() {
    private val _userSignUpState = mutableStateOf(SignUpState())
    val userSignUpState: State<SignUpState> = _userSignUpState

    fun signUpUser(email: String, password: String, username: String) {
        viewModelScope.launch {
            user.signUp(email, password, username).collect { result ->
                when(result) {
                    is Resource.Success -> _userSignUpState.value = SignUpState(isSignUp = true)
                    is Resource.Error -> _userSignUpState.value = SignUpState(error = result.message ?: "Error")
                    is Resource.Loading -> _userSignUpState.value = SignUpState(isLoading = true)
                }
            }
        }
    }
}