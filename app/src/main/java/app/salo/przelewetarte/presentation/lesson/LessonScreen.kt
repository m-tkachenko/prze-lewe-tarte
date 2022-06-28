package app.salo.przelewetarte.presentation.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.components.LeweProgressBar
import app.salo.przelewetarte.presentation.components.OrangeButton
import app.salo.przelewetarte.presentation.components.SadTopBar
import app.salo.przelewetarte.presentation.home.HomeViewModel
import coil.compose.AsyncImage

@Composable
fun LessonScreen(
    lessonViewModel: LessonViewModel = hiltViewModel(),
    navController: NavController
) {
    val InterFont = FontFamily(
        Font(R.font.inter_font, weight = FontWeight.Light),
        Font(R.font.inter_bold_font, weight = FontWeight.Bold)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SadTopBar(
                navController = navController,
                mainText = "Lesson 1",
                route = Screen.HomeScreen.route
            )

            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .height(200.dp)
                    .padding(
                        horizontal = 20.dp
                    )
                    .border(
                        width = 4.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    ),
                elevation = 5.dp
            ) {
                AsyncImage(
                    model = lessonViewModel.images[0],
                    contentDescription = "image",
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.
                    padding(horizontal = 20.dp),
                text = stringResource(id = lessonViewModel.textForLessons[0]),
                fontSize = 20.sp,
                fontFamily = InterFont,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF14261F)
            )

            Spacer(modifier = Modifier.height(40.dp))

            OrangeButton(
                onClick = {
                    navController.navigate(Screen.CameraScreen.route)
                },
                textInButton = "Share your work",
                textSize = 32.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}