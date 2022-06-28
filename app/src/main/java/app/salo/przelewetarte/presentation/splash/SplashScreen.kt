package app.salo.przelewetarte.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.Screen

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        if(splashViewModel.isUserAuthenticated)
            navController.navigate(Screen.HomeScreen.route)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "Splash screen",
            contentScale = ContentScale.Crop
        )
    }
}