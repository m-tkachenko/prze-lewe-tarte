package app.salo.przelewetarte.presentation.profile.states

import android.net.Uri

data class GetPhotosState(
    val isLoading: Boolean = false,
    val error: String = "",
    val photos: List<Uri> = emptyList()
)
