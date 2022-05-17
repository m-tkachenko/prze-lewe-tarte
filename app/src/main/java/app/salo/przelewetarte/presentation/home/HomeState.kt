package app.salo.przelewetarte.presentation.home

data class HomeState (
    val isLoading: Boolean = false,
    val error: String = "",
    val isSignOut: Boolean = false
)