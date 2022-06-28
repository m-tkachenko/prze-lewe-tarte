package app.salo.przelewetarte.domain.use_case

import app.salo.przelewetarte.domain.use_case.user_main.*

data class UserAuthUseCases(
    val signIn: UserSignInUseCase,
    val signOut: UserSignOutUseCase,
    val signUp: UserSignUpUseCase,
    val isUserAuthenticated: UserAlreadyAuthenticatedUseCase,
    val username: UserGetUsenameUseCase
)
