package app.salo.przelewetarte.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.auth.components.AppNameText
import app.salo.przelewetarte.presentation.auth.components.SignInCard
import app.salo.przelewetarte.presentation.auth.components.SignUpCard

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    var modeOfAuth by remember {
        mutableStateOf(AuthMode.SIGN_IN_MODE)
    }

    LaunchedEffect(Unit) {
        if(viewModel.isUserAuthenticated)
            navController.navigate(Screen.HomeScreen.route)

        viewModel.authMode.collect {
            modeOfAuth = it
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                AppNameText()

                if (modeOfAuth == AuthMode.SIGN_UP_MODE)
                    Image(
                        painter = painterResource(id = R.drawable.hmmm_face),
                        contentDescription = null
                    )
                else
                    Image(
                        painter = painterResource(id = R.drawable.smile_face),
                        contentDescription = null
                    )
            }

            if (modeOfAuth == AuthMode.SIGN_UP_MODE)
                SignUpCard(
                    navController = navController
                )
            else
                SignInCard(
                    navController = navController
                )

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.hand),
//                        contentDescription = null
//                    )
//
//                    Image(
//                        painter = painterResource(id = R.drawable.pencil),
//                        contentDescription = null
//                    )
//                }
        }
    }
}

//Button(
//onClick = {
//    viewModel.signInUser(email = "test2@gmail.com", password = "12345678")
//},
//colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
//) {
//    Text(text = "Sign in")
//}
//
//Spacer(modifier = Modifier.padding(30.dp))
//
//Button(
//onClick = {
//    navController.navigate(Screen.SignUpScreen.route)
//},
//colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
//) {
//    Text(text = "Go to register form")
//}
//
//Spacer(modifier = Modifier.padding(30.dp))
//