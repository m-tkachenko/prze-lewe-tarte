package app.salo.przelewetarte.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.use_case.UserMainUseCases
import app.salo.przelewetarte.domain.use_case.UserValidationUseCases
import app.salo.przelewetarte.presentation.auth.events.SignInEvent
import app.salo.przelewetarte.presentation.auth.events.SignUpEvent
import app.salo.przelewetarte.presentation.auth.states.SignInFormState
import app.salo.przelewetarte.presentation.auth.states.SignInState
import app.salo.przelewetarte.presentation.auth.states.SignUpFormState
import app.salo.przelewetarte.presentation.auth.states.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val user: UserMainUseCases,
    private val validation: UserValidationUseCases
): ViewModel() {
    val isUserAuthenticated get() = user.isUserAuthenticated()

    val authMode = MutableStateFlow(AuthMode.SIGN_IN_MODE)
    val validationResult = MutableStateFlow(false)

    private val _userSignInState = mutableStateOf(SignInState())
    val userSignInState: State<SignInState> = _userSignInState

    private val _userSignUpState = mutableStateOf(SignUpState())
    val userSignUpState: State<SignUpState> = _userSignUpState

    var signUpFormState by mutableStateOf(SignUpFormState())
    var signInFormState by mutableStateOf(SignInFormState())

    private fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            user.signIn(email, password).collect { result ->
                when(result) {
                    is Resource.Success -> _userSignInState.value = SignInState(isSignIn = true)
                    is Resource.Error -> _userSignInState.value = SignInState(error = result.message ?: "Error")
                    is Resource.Loading -> _userSignInState.value = SignInState(isLoading = true)
                }
            }
        }
    }

    private fun signUpUser(email: String, password: String, username: String) {
        viewModelScope.launch {
            user.signUp(email, password, username).collect { result ->
                when(result) {
                    is Resource.Success ->
                        _userSignUpState.value = SignUpState(isSignUp = true)
                    is Resource.Error ->
                        _userSignUpState.value = SignUpState(error = result.message ?: "Error")
                    is Resource.Loading ->
                        _userSignUpState.value = SignUpState(isLoading = true)
                }
            }
        }
    }

    fun onSignInEvent(event: SignInEvent) {
        when(event) {
            is SignInEvent.EmailChanged -> {
                signInFormState =
                    signInFormState.copy(email = event.email)
            }
            is SignInEvent.PasswordChanged -> {
                signInFormState =
                    signInFormState.copy(password = event.password)
            }
            is SignInEvent.SignInSubmit -> {
                signInSubmitForm()
            }
        }
    }

    fun onSignUpEvent(event: SignUpEvent) {
        when(event) {
            is SignUpEvent.EmailChanged -> {
                signUpFormState =
                    signUpFormState.copy(email = event.email)
            }
            is SignUpEvent.PasswordChanged -> {
                signUpFormState =
                    signUpFormState.copy(password = event.password)
            }
            is SignUpEvent.TermsChanged -> {
                signUpFormState =
                    signUpFormState.copy(acceptedTerms = event.terms)
            }
            is SignUpEvent.NameChanged -> {
                signUpFormState =
                    signUpFormState.copy(username = event.username)
            }
            is SignUpEvent.SignUpSubmit -> {
                signUpSubmitForm()
            }
        }
    }

    private fun signInSubmitForm() {
        val emailResult = validation.email(signInFormState.email)
        val passwordResult = validation.password(signInFormState.password)

        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any { !it.isSuccess }

        if (hasError) {
            signInFormState = signInFormState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            validationResult.value = false
            return
        }

        validationResult.value = true
        signInUser(
            email = signInFormState.email,
            password = signInFormState.password
        )
    }

    private fun signUpSubmitForm() {
        val emailResult = validation.email(signUpFormState.email)
        val passwordResult = validation.password(signUpFormState.password)
        val termsResult = validation.terms(signUpFormState.acceptedTerms)
        val usernameResult = validation.username(signUpFormState.username)

        val hasError = listOf(
            emailResult,
            passwordResult,
            termsResult,
            usernameResult
        ).any { !it.isSuccess }

        if (hasError) {
            signUpFormState = signUpFormState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                acceptedTermsError = termsResult.errorMessage,
                usernameError = usernameResult.errorMessage
            )
            validationResult.value = false
            return
        }

        validationResult.value = true
        signUpUser(
            email = signUpFormState.email,
            password = signUpFormState.password,
            username = signUpFormState.username
        )
    }
}