package app.salo.przelewetarte.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.home.components.FunnyTopBar
import app.salo.przelewetarte.presentation.home.components.PrettyItem

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val usernameState = homeViewModel.getUsernameState.value
    var username by remember { mutableStateOf("Loading...") }

    LaunchedEffect(Unit) {
        homeViewModel.getUsername()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            FunnyTopBar(
                username = username,
                imageId = R.drawable.profile_image_3,
                navController = navController
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 8.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                items(count = homeViewModel.lessonQuantity) {
                    PrettyItem(
                        img = homeViewModel.images[it],
                        titleText = "Lesson ${it + 1}",
                        descriptionTextId = homeViewModel.descroptionTestIds[it],
                        onClick = {
                            navController.navigate(Screen.LessonScreen.route + "/$it")
                        }
                    )
                }
            }
        }

        if (usernameState.isLoading) {
            username = "Loading..."
        }
        if (usernameState.error.isNotEmpty()) {
            Log.d("PUPPY", "Error")
        }
        if (usernameState.username.isNotEmpty()) {
            username = "Hello, ${usernameState.username}!"
        }
    }
}