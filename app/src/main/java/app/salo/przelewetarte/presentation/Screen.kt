package app.salo.przelewetarte.presentation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object SignInScreen: Screen("sign_in_screen")
    object SignUpScreen: Screen("sign_up_screen")
}