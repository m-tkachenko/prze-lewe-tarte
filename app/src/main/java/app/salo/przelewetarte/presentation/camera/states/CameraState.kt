package app.salo.przelewetarte.presentation.camera.states

data class CameraState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isSuccess: Boolean = false
)
