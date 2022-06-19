package app.salo.przelewetarte.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.salo.przelewetarte.R
import app.salo.przelewetarte.common.Materials
import app.salo.przelewetarte.domain.use_case.UserMainUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val user: UserMainUseCases
): ViewModel() {
    private val materials = Materials()

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    val userName = "Shaprashak"
    val profileImageId = R.drawable.profile_image_2

    val lessonQuantity = materials.images.size
    val images = materials.images.toList()
    val descroptionTestIds = materials.descriptions.toList()

    fun signOutUser() {
        viewModelScope.launch {
            user.signOut()
        }
    }
}