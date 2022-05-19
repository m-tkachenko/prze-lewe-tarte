package app.salo.przelewetarte.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.salo.przelewetarte.presentation.home.HomeScreen
import app.salo.przelewetarte.presentation.sign_in.SignInScreen
import app.salo.przelewetarte.presentation.sign_up.SignUpScreen
import app.salo.przelewetarte.presentation.theme.PrzelewetarteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrzelewetarteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.SignInScreen.route
                    ) {
                        composable(
                            route = Screen.SignInScreen.route
                        ) {
                            SignInScreen(navController = navController)
                        }

                        composable(
                            route = Screen.HomeScreen.route
                        ) {
                            HomeScreen(navController = navController)
                        }

                        composable(
                            route = Screen.SignUpScreen.route
                        ) {
                            SignUpScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}