package app.salo.przelewetarte.presentation.auth.states

data class SignUpState(
    val isLoading: Boolean = false,
    val isSignUp: Boolean = false,
    val error: String = "",
    val isAddUsername: Boolean = false
)