package app.salo.przelewetarte.presentation.lesson

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.components.OrangeButton
import app.salo.przelewetarte.presentation.components.SadTopBar

@Composable
fun LessonScreen(
    lessonViewModel: LessonViewModel = hiltViewModel(),
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SadTopBar(
            navController = navController,
            mainText = "Profile",
            route = Screen.HomeScreen.route
        )
    }
}