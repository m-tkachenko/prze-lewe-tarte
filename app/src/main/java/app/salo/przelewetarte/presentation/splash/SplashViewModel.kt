package app.salo.przelewetarte.presentation.splash

import androidx.lifecycle.ViewModel
import app.salo.przelewetarte.domain.use_case.UserAuthUseCases
import app.salo.przelewetarte.domain.use_case.UserValidationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val user: UserAuthUseCases,
): ViewModel() {
    val isUserAuthenticated get() = user.isUserAuthenticated()
}