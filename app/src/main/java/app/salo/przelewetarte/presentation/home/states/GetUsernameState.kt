package app.salo.przelewetarte.presentation.home.states

data class GetUsernameState(
    val isLoading: Boolean = false,
    val error: String = "",
    val username: String = ""
)