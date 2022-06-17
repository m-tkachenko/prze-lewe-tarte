package app.salo.przelewetarte.presentation.sign_in

data class AuthState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isSignIn: Boolean = false
)