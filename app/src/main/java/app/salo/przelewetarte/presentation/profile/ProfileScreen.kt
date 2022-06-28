package app.salo.przelewetarte.presentation.profile

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.components.LeweProgressBar
import app.salo.przelewetarte.presentation.components.OrangeButton
import app.salo.przelewetarte.presentation.components.SadTopBar
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import kotlin.random.Random

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    val getPhotosState = profileViewModel.getPhotosState.value
    val usernameState = profileViewModel.getUsernameState.value
    var username by remember { mutableStateOf("Loading...") }

    LaunchedEffect(Unit){
        profileViewModel.getPhotos()
        profileViewModel.getUsername()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SadTopBar(
                navController = navController,
                mainText = "Profile",
                route = Screen.HomeScreen.route
            )

            Image(
                painter = painterResource(id = R.drawable.profile_image_3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(
                        width = 5.dp,
                        color = Color(0xFF14261F),
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = username,
                textAlign = TextAlign.Center,
                fontFamily = MaterialTheme.typography.body1.fontFamily,
                fontSize = 30.sp,
                color = Color(0xFF14261F),
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OrangeButton(
                    onClick = {
                        profileViewModel.signOutUser()
                        navController.navigate(Screen.AuthScreen.route)
                    },
                    textInButton = "SignOut",
                    textSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                text = "Your gallery",
                textAlign = TextAlign.Start,
                fontFamily = MaterialTheme.typography.body1.fontFamily,
                fontSize = 28.sp,
                color = Color(0xFF14261F),
            )

            if (usernameState.isLoading) {
                username = "Loading..."
            }
            if (usernameState.error.isNotEmpty()) {
                Log.d("PUPPY", "Error")
            }
            if (usernameState.username.isNotEmpty()) {
                username = usernameState.username
            }

            if (getPhotosState.isLoading && getPhotosState.photos.isEmpty()) {
                Spacer(modifier = Modifier.height(130.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = "You don't have images :(",
                    textAlign = TextAlign.Center,
                    fontFamily = MaterialTheme.typography.body1.fontFamily,
                    fontSize = 34.sp,
                    color = Color(0xFF14261F),
                )
            }
            else if (getPhotosState.isLoading) {
                Spacer(modifier = Modifier.height(110.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 12.dp
                        ),
                    text = "Loading...",
                    textAlign = TextAlign.Center,
                    fontFamily = MaterialTheme.typography.body1.fontFamily,
                    fontSize = 32.sp,
                    color = Color(0xFF14261F),
                )
                LeweProgressBar(width = 0.6f)
            }

            if (getPhotosState.error.isNotEmpty()) {
                Spacer(modifier = Modifier.height(145.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = getPhotosState.error,
                    textAlign = TextAlign.Center,
                    fontFamily = MaterialTheme.typography.body1.fontFamily,
                    fontSize = 32.sp,
                    color = MaterialTheme.colors.error,
                )
            }

            if (getPhotosState.photos.isNotEmpty()) {
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(150.dp),
                    modifier = Modifier
                        .padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        )
                ) {
                    items(getPhotosState.photos) { photo ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = MaterialTheme.colors.onPrimary,
                            modifier = Modifier
                                .height(250.dp)
                                .padding(all = 8.dp)
                                .border(
                                    width = 4.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            elevation = 5.dp
                        ) {
                            AsyncImage(
                                model = photo,
                                contentDescription = "image",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

//            LazyColumn(
//
//            ) {
//                itemsIndexed((getPhotosState.photos)) { row, photo ->
//                    LazyRow() {
//                        itemsIndexed((1..2).map { it }) { _, _ ->
//                            Card(
//                                shape = RoundedCornerShape(8.dp),
//                                backgroundColor = MaterialTheme.colors.onPrimary,
//                                modifier = Modifier
//                                    .height(200.dp)
//                                    .padding(all = 8.dp)
//                                    .border(
//                                        width = 4.dp,
//                                        color = Color.Black,
//                                        shape = RoundedCornerShape(8.dp)
//                                    ),
//                                elevation = 5.dp
//                            ) {
//                                AsyncImage(
//                                    model = photo,
//                                    contentDescription = "image",
//                                    contentScale = ContentScale.Crop
//                                )
//                            }
//                        }
//                    }
//                }
//            }

        }
    }
}