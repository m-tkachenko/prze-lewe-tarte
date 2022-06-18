package app.salo.przelewetarte.presentation.auth.states

data class SignUpFormState(
    val email: String = "",
    val emailError: String? = null,

    val password: String = "",
    val passwordError: String? = null,

    val acceptedTerms: Boolean = false,
    val acceptedTermsError: String? = null,

    val username: String = "",
    val usernameError: String? = null
)