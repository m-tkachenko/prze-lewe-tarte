package app.salo.przelewetarte.presentation.sign_in

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

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
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    viewModel.signInUser(email = "test2@gmail.com", password = "12345678")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
            ) {
                Text(text = "Sign in")
            }
            
            Spacer(modifier = Modifier.padding(30.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.SignUpScreen.route)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
            ) {
                Text(text = "Go to register form")
            }

            Spacer(modifier = Modifier.padding(30.dp))

            if (signInState.isLoading)
                CircularProgressIndicator()

            if (signInState.isSignIn)
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.HomeScreen.route)
                }

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
}