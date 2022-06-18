package app.salo.przelewetarte.presentation.auth.states

data class SignInState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isSignIn: Boolean = false
)