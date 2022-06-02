package app.salo.przelewetarte.domain.use_case

import app.salo.przelewetarte.domain.use_case.user_main.UserAlreadyAuthenticatedUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignInUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignOutUseCase
import app.salo.przelewetarte.domain.use_case.user_main.UserSignUpUseCase

data class UserMainUseCases(
    val signIn: UserSignInUseCase,
    val signOut: UserSignOutUseCase,
    val signUp: UserSignUpUseCase,
    val isUserAuthenticated: UserAlreadyAuthenticatedUseCase
)
