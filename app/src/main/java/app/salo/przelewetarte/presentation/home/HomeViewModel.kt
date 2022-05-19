package app.salo.przelewetarte.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.use_case.UserAuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userAuthentication: UserAuthenticationUseCases
): ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    fun signOutUser() {
        viewModelScope.launch {
            userAuthentication.signOut()
        }
    }
}