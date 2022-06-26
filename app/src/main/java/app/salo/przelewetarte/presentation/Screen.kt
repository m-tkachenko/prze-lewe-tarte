package app.salo.przelewetarte.presentation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object AuthScreen: Screen("auth_screen")
    object HomeScreen: Screen("home_screen")
    object ProfileScreen: Screen("profile_screen")
    object LessonScreen: Screen("lesson_screen")
    object CameraScreen: Screen("camera_screen")
}