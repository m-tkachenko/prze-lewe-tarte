package app.salo.przelewetarte.presentation.auth.events

sealed class SignInEvent {
    data class EmailChanged(val email: String): SignInEvent()
    data class PasswordChanged(val password: String): SignInEvent()

    object SignInSubmit: SignInEvent()
}
