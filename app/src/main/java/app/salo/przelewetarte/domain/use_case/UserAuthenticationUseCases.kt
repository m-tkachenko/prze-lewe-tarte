package app.salo.przelewetarte.domain.use_case

import app.salo.przelewetarte.domain.use_case.user_authentication.UserAlreadyAuthenticatedUseCase
import app.salo.przelewetarte.domain.use_case.user_authentication.UserSignInUseCase
import app.salo.przelewetarte.domain.use_case.user_authentication.UserSignOutUseCase
import app.salo.przelewetarte.domain.use_case.user_authentication.UserSignUpUseCase

data class UserAuthenticationUseCases(
    val signIn: UserSignInUseCase,
    val signOut: UserSignOutUseCase,
    val signUp: UserSignUpUseCase,
    val isUserAuthenticated: UserAlreadyAuthenticatedUseCase
)
