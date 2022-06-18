package app.salo.przelewetarte.presentation.auth.states

data class SignInFormState(
    val email: String = "",
    val emailError: String? = null,

    val password: String = "",
    val passwordError: String? = null,
)
