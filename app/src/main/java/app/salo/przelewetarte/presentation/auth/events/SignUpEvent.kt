package app.salo.przelewetarte.presentation.auth.events

sealed class SignUpEvent {
    data class EmailChanged(val email: String): SignUpEvent()
    data class PasswordChanged(val password: String): SignUpEvent()
    data class TermsChanged(val terms: Boolean): SignUpEvent()
    data class NameChanged(val username: String): SignUpEvent()

    object SignUpSubmit: SignUpEvent()
}
