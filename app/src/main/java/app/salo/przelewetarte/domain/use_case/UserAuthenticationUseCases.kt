package app.salo.przelewetarte.domain.use_case

import app.salo.przelewetarte.domain.use_case.user_authentication.UserAlreadyAuthenticatedUseCase
import app.salo.przelewetarte.domain.use_case.user_authentication.UserSignInUseCase
import app.salo.przelewetarte.domain.use_case.user_authentication.UserSignOutUseCase

data class UserAuthenticationUseCases(
    val signIn: UserSignInUseCase,
    val signOut: UserSignOutUseCase,
    val isUserAuthenticated: UserAlreadyAuthenticatedUseCase
)
