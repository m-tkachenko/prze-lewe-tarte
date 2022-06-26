package app.salo.przelewetarte.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import app.salo.przelewetarte.common.Materials
import app.salo.przelewetarte.domain.use_case.UserAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val user: UserAuthUseCases
): ViewModel() {
    private val materials = Materials()
    val username = "Brian"

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    val lessonQuantity = materials.images.size
    val images = materials.images.toList()
    val descroptionTestIds = materials.descriptions.toList()
}