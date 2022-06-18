package app.salo.przelewetarte.presentation.auth.components

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.components.BeautifulTextField
import app.salo.przelewetarte.presentation.components.OrangeButton
import app.salo.przelewetarte.presentation.auth.AuthMode
import app.salo.przelewetarte.presentation.auth.AuthViewModel
import app.salo.przelewetarte.presentation.auth.events.SignInEvent

@Composable
fun SignInCard(
    signInViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val signInState = signInViewModel.userSignInState.value
    val signInValidationState = signInViewModel.signInFormState

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .offset(y = (-35).dp)
            .border(
                width = 4.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 25.dp)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OrangeButton(
                    onClick = { /* NOTHING :) */ },
                    paddingHorizontal = 16.dp,
                    textInButton = "sign in",
                    textSize = 16.sp
                )

                OrangeButton(
                    onClick = { signInViewModel.authMode.value = AuthMode.SIGN_UP_MODE },
                    paddingHorizontal = 16.dp,
                    textInButton = "register",
                    textSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            BeautifulTextField(
                labelText = "Email",
                value = signInValidationState.email,
                onValueChanged = {
                    signInViewModel.onSignInEvent(
                        SignInEvent.EmailChanged(it)
                    )
                },
                isError = signInValidationState.emailError != null,
                errorText = signInValidationState.emailError ?: "Smth wrong"
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            BeautifulTextField(
                labelText = "Password",
                value = signInValidationState.password,
                onValueChanged = {
                    signInViewModel.onSignInEvent(
                        SignInEvent.PasswordChanged(it)
                    )
                },
                isError = signInValidationState.passwordError != null,
                errorText = signInValidationState.passwordError ?: "Smth wrong"
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (!signInState.isLoading)
                OrangeButton(
                    onClick = {
                        signInViewModel.onSignInEvent(
                            SignInEvent.SignInSubmit
                        )
                    },
                    textInButton = "Sign In",
                    textSize = 24.sp,
                    fillMaxWidth = true,
                    paddingVertical = 5.dp
                )
            else
                CircularProgressIndicator()

            if (signInState.error.isNotBlank())
                Toast.makeText(
                    context,
                    signInState.error,
                    Toast.LENGTH_SHORT
                ).show()

            if (signInState.isSignIn)
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.HomeScreen.route)
                }
        }
    }
}