package app.salo.przelewetarte.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.home.components.FunnyTopBar
import app.salo.przelewetarte.presentation.home.components.PrettyItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val homeState = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            FunnyTopBar(
                username = viewModel.userName,
                imageId = viewModel.profileImageId,
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                items(count = viewModel.lessonQuantity) {
                    PrettyItem(
                        img = viewModel.images[it],
                        titleText = "Lesson ${it + 1}",
                        descriptionTextId = viewModel.descroptionTestIds[it]
                    )
                }
            }
        }
    }
}