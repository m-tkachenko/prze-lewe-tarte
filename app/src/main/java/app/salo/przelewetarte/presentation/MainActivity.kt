package app.salo.przelewetarte.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.salo.przelewetarte.presentation.home.HomeScreen
import app.salo.przelewetarte.presentation.auth.AuthScreen
import app.salo.przelewetarte.presentation.lesson.LessonScreen
import app.salo.przelewetarte.presentation.profile.ProfileScreen
import app.salo.przelewetarte.presentation.theme.ui.PrzelewetarteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrzelewetarteTheme {
                window?.statusBarColor = MaterialTheme.colors.background.toArgb()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.AuthScreen.route
                    ) {
                        composable(
                            route = Screen.AuthScreen.route
                        ) {
                            AuthScreen(navController = navController)
                        }

                        composable(
                            route = Screen.HomeScreen.route
                        ) {
                            HomeScreen(navController = navController)
                        }

                        composable(
                            route = Screen.ProfileScreen.route
                        ) {
                            ProfileScreen(navController = navController)
                        }

                        composable(
                            route = Screen.LessonScreen.route + "/{lessonId:}"
                        ) {
                            LessonScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}