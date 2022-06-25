package app.salo.przelewetarte.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.components.OrangeButton

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OrangeButton(
            onClick = {
                navController.navigate(Screen.CameraScreen.route)
            },
            textInButton = "Camera",
            textSize = 12.sp
        )
    }
}