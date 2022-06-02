package app.salo.przelewetarte.presentation.sign_up

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.Screen

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    val signUpState = viewModel.userSignUpState.value
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    viewModel.signUpUser("test2@gmail.com", "12345678", "test2")
                },
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(text = "Sign up")
            }

            Spacer(modifier = Modifier.padding(30.dp))

            if (signUpState.isSignUp)
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.HomeScreen.route)
                }
            if (signUpState.isLoading)
                CircularProgressIndicator()
            if (signUpState.error.isNotBlank())
                Text(
                    text = signUpState.error,
                    textAlign = TextAlign.Center,
                )
        }
    }
}