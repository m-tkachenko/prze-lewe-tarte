package app.salo.przelewetarte.presentation.sign_up

data class SignUpState(
    val isLoading: Boolean = false,
    val isSignUp: Boolean = false,
    val error: String = ""
)