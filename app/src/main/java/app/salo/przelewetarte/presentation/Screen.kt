package app.salo.przelewetarte.presentation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object AuthScreen: Screen("auth_screen")
    object LessonScreen: Screen("lesson_screen")
    object ProfileScreen: Screen("profile_screen")
}