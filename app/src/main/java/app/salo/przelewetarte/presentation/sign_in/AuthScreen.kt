package app.salo.przelewetarte.presentation.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.sign_in.components.AppNameText
import app.salo.przelewetarte.presentation.sign_in.components.SignUpCard

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
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
                
                Image(
                    painter = painterResource(id = R.drawable.hmmm_face),
                    contentDescription = null
                )
            }

//            LogInCard()
            SignUpCard()

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.hand),
//                    contentDescription = null
//                )
//
//                Image(
//                    painter = painterResource(id = R.drawable.pencil),
//                    contentDescription = null
//                )
//            }
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
//if (signInState.isLoading)
//CircularProgressIndicator()
//
//if (signInState.isSignIn)
//LaunchedEffect(Unit) {
//    navController.navigate(Screen.HomeScreen.route)
//}
//
//if (signInState.error.isNotBlank()) {
//    Text(
//        text = signInState.error,
//        textAlign = TextAlign.Center,
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentHeight()
//    )
//}