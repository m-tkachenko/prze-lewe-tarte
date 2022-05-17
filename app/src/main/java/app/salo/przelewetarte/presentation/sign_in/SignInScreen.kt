package app.salo.przelewetarte.presentation.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.Screen

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    val signInState = viewModel.state.value

    LaunchedEffect(Unit) {
        if(viewModel.isUserAuthenticated)
            navController.navigate(Screen.HomeScreen.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Button(
            onClick = {
                viewModel.signInUser(email = "email@gmail.com", password = "12345678")
            },
            modifier = Modifier.align(Alignment.Center),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
        ) {
            Text(text = "Sign in")
        }

        if (signInState.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        if (signInState.isSignIn)
            navController.navigate(Screen.HomeScreen.route)

        if (signInState.error.isNotBlank()) {
            Text(
                text = signInState.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            )
        }
    }


}