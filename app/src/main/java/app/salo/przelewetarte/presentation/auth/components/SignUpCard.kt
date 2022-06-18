package app.salo.przelewetarte.presentation.auth.components

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.auth.events.SignUpEvent
import app.salo.przelewetarte.presentation.components.BeautifulTextField
import app.salo.przelewetarte.presentation.components.OrangeButton
import app.salo.przelewetarte.presentation.auth.AuthMode
import app.salo.przelewetarte.presentation.auth.AuthViewModel

@Composable
fun SignUpCard(
    signUpViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val signUpState = signUpViewModel.userSignUpState.value
    val signUpValidationState = signUpViewModel.signUpFormState

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .offset(y = (-20).dp)
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
                    onClick = { signUpViewModel.authMode.value = AuthMode.SIGN_IN_MODE },
                    paddingHorizontal = 16.dp,
                    textInButton = "sign in",
                    textSize = 16.sp
                )

                OrangeButton(
                    onClick = { /* NOTHING :) */ },
                    paddingHorizontal = 16.dp,
                    textInButton = "register",
                    textSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            BeautifulTextField(
                labelText = "Name",
                value = signUpValidationState.username,
                onValueChanged = {
                    signUpViewModel.onSignUpEvent(
                        SignUpEvent.NameChanged(it)
                    )
                },
                isError = signUpValidationState.usernameError != null,
                errorText = signUpValidationState.usernameError ?: "Smth wrong"
            )

            Spacer(modifier = Modifier.height(16.dp))

            BeautifulTextField(
                labelText = "Email",
                value = signUpValidationState.email,
                onValueChanged = {
                    signUpViewModel.onSignUpEvent(
                        SignUpEvent.EmailChanged(it)
                    )
                },
                isError = signUpValidationState.emailError != null,
                errorText = signUpValidationState.emailError ?: "Smth wrong"
            )

            Spacer(modifier = Modifier.height(16.dp))

            BeautifulTextField(
                labelText = "Password",
                value = signUpValidationState.password,
                onValueChanged = {
                    signUpViewModel.onSignUpEvent(
                        SignUpEvent.PasswordChanged(it)
                    )
                },
                isError = signUpValidationState.passwordError != null,
                errorText = signUpValidationState.passwordError ?: "Smth wrong"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .offset(x = (-13).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = signUpValidationState.acceptedTerms,
                    onCheckedChange = {
                        signUpViewModel.onSignUpEvent(
                            SignUpEvent.TermsChanged(it)
                        )
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = Color(0xFF071224),
                        uncheckedColor = Color(0xFF14261F),
                    )
                )

                Text(
                    text = "Accept Terms of Service",
                    color =
                        if (signUpValidationState.acceptedTermsError != null)
                            MaterialTheme.colors.error
                        else
                            Color(0xFF071224)
                )
            }

            if (!signUpState.isLoading)
                OrangeButton(
                    onClick = {
                        signUpViewModel.onSignUpEvent(
                            SignUpEvent.SignUpSubmit
                        )
                    },
                    textInButton = "Register",
                    textSize = 24.sp,
                    fillMaxWidth = true,
                    paddingVertical = 5.dp
                )
            else
                CircularProgressIndicator()

            if (signUpState.error.isNotBlank())
                Toast.makeText(
                    context,
                    signUpState.error,
                    Toast.LENGTH_SHORT
                ).show()
            if (signUpState.isSignUp)
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.HomeScreen.route)
                }

        }
    }
}